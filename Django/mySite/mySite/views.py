from django.shortcuts import render, get_object_or_404
import os
from storage.models import TestData as td

# Create your views here.

def index(request):
  context = {
    'datalist': td.objects.all(),
  }  
  return render(request, 'mySite/index.html', context)

def form(request):
  context = {
    'datalist': [],
  }  
  return render(request, 'mySite/form.html', context)

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
  return render(request, 'mySite/form.html', context)

def mlPractice(request):
  markdownText = open(os.path.join(os.path.dirname(__file__), 'markdowns/mlPractice.md')).read()
  print(markdownText)
  context = {
    'markdownText': markdownText,
    'datalist': ['abc'],
  }  
  return render(request, 'mySite/projects/mlPractice.html', context)


def layoutStatic(request):  
  return render(request, 'mySite/layoutStatic.html')

def layoutSidenavLight(request):  
  return render(request, 'mySite/layoutSidenavLight.html')


###


def login(request):  
  return render(request, 'mySite/login.html')

def _401(request):  
  return render(request, 'mySite/401.html')

# def _404(request):  
#   return render(request, 'mySite/404.html')

def _500(request):  
  return render(request, 'mySite/500.html')

def register(request):  
  return render(request, 'mySite/register.html')

def password(request):  
  return render(request, 'mySite/password.html')

def charts(request):  
  return render(request, 'mySite/charts.html')

def tables(request):  
  return render(request, 'mySite/tables.html')
