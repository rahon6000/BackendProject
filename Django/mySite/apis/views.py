from typing import Any
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from django.views import View

# import rest_framework as rest # 이거 좋긴 함 만든 model 에 대해 자동으로 REST method 만들어줌.

# Create your views here.

def index(request):
  return HttpResponse("indexing APIs")

class count(View):
      
  def get(self, request):
    # return JsonResponse({'count':myCount.count}) # This works good.
    return HttpResponse(myCount.getCount()) # This works fine.



class myCount:
  count = 0
  
  @staticmethod # Dont use self in static method.
  def getCount():
    myCount.count += 1
    return myCount.count