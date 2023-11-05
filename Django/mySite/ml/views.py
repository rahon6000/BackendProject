from django.shortcuts import render
from django.http  import HttpRequest
from django.shortcuts import render
import os
# Create your views here.

def index(request:HttpRequest):
  markdownText = open(
    os.path.join(os.path.dirname(__file__), 'markdowns/mlPractice.md')).read()
  
  context = {
    'markdownText': markdownText,
    'datalist': ['abc'],
  }
  return render(request, 'mysite/mlIndex.html', context=context)

