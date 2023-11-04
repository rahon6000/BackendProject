import datetime
from typing import Any
from django.http import HttpRequest, HttpResponse, JsonResponse
# from django.contrib.sessions.backends.db import SessionStore ## 따로 세션 꾸러미를 유지하고 싶을 때 (?)
from django.contrib.sessions.models import Session
from django.shortcuts import render
from django.views import View
from django.utils import timezone as tz
from . import models

# import rest_framework as rest # 이거 좋긴 함 만든 model 에 대해 자동으로 REST method 만들어줌.

# Create your views here.

def index(request):
  return HttpResponse("indexing APIs")

class count(View):
  def get(self, request: HttpRequest):
    count = MyCount.getCount(request)
    return JsonResponse({
      'today':count[0], 
      'total':count[1]}) # This works good.
    # return HttpResponse(MyCount.getCount(request)) # This works fine.

# Actually you can clear sessions with `python manage.py clearsessions`
class resetSessions(View):
  def get(self, request: HttpRequest):
    num = Session.objects.count()
    Session.objects.all().delete()
    print( str(num) + ' sesseion(s) is(are) cleared.')
    return HttpResponse('session cleared.')

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
