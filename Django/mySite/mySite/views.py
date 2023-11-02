from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse
import os
from myapp.models import TestData as td

# Create your views here.

def landing(request):
  return HttpResponse("landing!")

def index(request):
  context = {
    'datalist': td.objects.all(),
  }  
  return render(request, 'mysite/index.html', context)

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

def mlPractice(request):
  markdownText = open(os.path.join(os.path.dirname(__file__), 'markdowns/mlPractice.md')).read()
  print(markdownText)
  context = {
    'markdownText': markdownText,
    'datalist': ['abc'],
  }  
  return render(request, 'mysite/projects/mlPractice.html', context)


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
