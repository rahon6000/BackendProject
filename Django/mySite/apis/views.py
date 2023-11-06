import datetime
from typing import Any
from django.http import HttpRequest, HttpResponse, JsonResponse
# from django.contrib.sessions.backends.db import SessionStore ## 따로 세션 꾸러미를 유지하고 싶을 때 (?)
from django.contrib.sessions.models import Session
from django.shortcuts import render
from django.views import View
from django.utils import timezone as tz
from . import models
from rest_framework.response import Response
from rest_framework.views import APIView
from . import serializers as sr

# import rest_framework as rest # 이거 좋긴 함 만든 model 에 대해 자동으로 REST method 만들어줌.

# Create your views here.

def index(request):
  return HttpResponse("indexing APIs")

# Actually you can clear sessions with `python manage.py clearsessions`
class ResetSessions(View):
  def get(self, request: HttpRequest):
    num = Session.objects.count()
    Session.objects.all().delete()
    print( str(num) + ' sesseion(s) is(are) cleared.')
    return HttpResponse('session cleared.')


class count(View):
  """count 방문자 카운터.

  방문자 세션 기준 하루 방문자를 카운터 해서 DB 에 넣는 메서드.
  REST framework 사용하지 않은 버전임.

  """  
  def get(self, request: HttpRequest):
    count = MyCount.getCount(request)
    return JsonResponse({
      'today':count[0], 
      'total':count[1]}) # This works good.
    # return HttpResponse(MyCount.getCount(request)) # This works fine.


class ColorScoreAPI(APIView):
  """ColorScoreAPI 컬러센스 데이터 API

  위에 있는 카운터랑 달리 REST framework 사용함.

  """  
  def get(self, request: HttpRequest):
    queryset = models.ColorScore.objects.all()
    serializer = sr.ColorScoreSerializer(queryset, many=True)
    return Response(serializer.data)
  
  def post(self, request: HttpRequest):
    serializer = sr.ColorScoreSerializer(data=request.data)
    if serializer.is_valid():
      serializer.save()
      return Response(serializer.data)  
    return Response(serializer.errors)
  
class ColorScoreAPI_average(APIView):
  """ColorScoreAPI_average 컬러센스 평균 API

  ColorScore 모델 안에 custom method 만들어서, 따로 만들어 봄.
  'color/getav' 와 연결됨.

  """  
  def get(self, request: HttpRequest):
    return Response(models.ColorScore.average())
    

##########
# logics #
##########

class MyCount:
  @staticmethod # Dont use self in static method.
  def getCount(request: HttpRequest):
    session = request.session
    today = tz.now().replace(hour=0, minute=0, second=0)
    tommorow = today + tz.timedelta(days=1) ## not python datetime, use django timezone.
    session.set_expiry(tommorow)
    # print(session.get('count', False)) False if client visited first here.
    if not (session.get('count', False)):
      session['count'] = True
      count = MyCount.record(today)
    else:
      count = models.Count.objects.last().today, models.Count.objects.last().total
    return count
    
  @staticmethod
  def record(date: datetime):
    date = date.strftime("%Y-%m-%d")
    if(models.Count.objects.count() == 0):
      today = models.Count.objects.create(date = date, total = 0, today = 0)
    elif (models.Count.objects.last().date.strftime("%Y-%m-%d") != date):
      today = models.Count.objects.create(date = date, 
                                          total = models.Count.objects.last().total,
                                          today = 0)
    else:
      today = models.Count.objects.last()
    today.total += 1
    today.today += 1
    today.save()
    return today.today, today.total
