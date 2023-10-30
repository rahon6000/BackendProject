from django.shortcuts import render, get_object_or_404
from . import models

# Create your views here.

def index(request):
  context = {
    'datalist': ['abc'],
  }  
  return render(request, 'mySite/index.html', context)

def mlPractice(request):
  context = {
    'datalist': ['abc'],
  }  
  return render(request, 'mySite/mlPractice.html', context)

def layoutStatic(request):  
  return render(request, 'mySite/layoutStatic.html')

def layoutSidenavLight(request):  
  return render(request, 'mySite/layoutSidenavLight.html')

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