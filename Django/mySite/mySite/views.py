from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse
from django.views import View
import os
from myapp.models import TestData as td
from datetime import datetime

# Create your views here.

def landing(request):
  servertime = datetime.today()
  
  return HttpResponse("landing!<br>"
                      "Static server time : " + servertime.strftime('%Y-%m-%d %HH:%MM:%SS'))

# INDEX
def index(request):
  context = {
    'datalist': td.objects.all(),
  }  
  return render(request, 'mysite/index.html', context)

# Form practice
def form(request):
  context = {
    'datalist': [],
  }  
  return render(request, 'mysite/form.html', context)

def formSubmit(request):
  data = td()
  if ( v:= request.POST['name'] ) != '': data.testData_name = v
  if ( v:= request.POST['position'] ) != '': data.testData_position = v
  if ( v:= request.POST['office'] ) != '': data.testData_office = v
  if ( v:= request.POST['age'] ) != '': data.testData_age = v
  if ( v:= request.POST['startDate'] ) != '': data.testData_startDate = v
  if ( v:= request.POST['salary'] ) != '': data.testData_salary = v
  data.save()
  context = {
    'datalist': td.objects.all(),
  }  
  return render(request, 'mysite/form.html', context)

# ML related
def mlPractice(request):
  markdownText = open(
    os.path.join(os.path.dirname(__file__), 'markdowns/mlPractice.md')).read()
  print(markdownText)
  context = {
    'markdownText': markdownText,
    'datalist': ['abc'],
  }  
  return render(request, 'mysite/projects/mlPractice.html', context)

# Pi playground
class PiPlay(View): # 좀 묘하긴 하지만 나쁘진 않은 듯...
  BASE_PATH = os.path.dirname(__file__)
  def get(self, request):
    markdownText = open(os.path.join(self.BASE_PATH, 'markdowns/piPlay.md')).read()
    context = {
      'markdownText': markdownText,
      'dummy': ['dummy', 'data'],
    }  
    return render(request, 'mysite/projects/piPlay.html', context)

  def post(self, request):
    markdownText = open(os.path.join(self.BASE_PATH, 'markdowns/piPlay.md')).read()
    context = {
      'markdownText': markdownText,
      'dummy': ['dummy', 'data'],
    }  
    return render(request, 'mysite/projects/piPlay.html', context)

  def getNthDigit(n):
    
    return 0

















def layoutStatic(request):  
  return render(request, 'mysite/layoutStatic.html')

def layoutSidenavLight(request):  
  return render(request, 'mysite/layoutSidenavLight.html')


###


def login(request):  
  return render(request, 'mysite/login.html')

def _401(request):  
  return render(request, 'mysite/401.html')

# def _404(request):  
#   return render(request, 'mysite/404.html')

def _500(request):  
  return render(request, 'mysite/500.html')

def register(request):  
  return render(request, 'mysite/register.html')

def password(request):  
  return render(request, 'mysite/password.html')

def charts(request):  
  return render(request, 'mysite/charts.html')

def tables(request):  
  return render(request, 'mysite/tables.html')
