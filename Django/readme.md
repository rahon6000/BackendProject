# Initialize
## venv 만들기
python -m venv djangoVenv

## venv activate
djangoVenv\Scripts\activate.bat
or
djangoVenv\Scripts\Ativate.ps1

## venv check
(djangoVenv) 있어야 함

## Starting project
django-admin startproject mySite (걍 터미널 명령어 쓸 수 있음.)

## Start (dev) server
python manage.py runserver 8080

## Create app inside project
python manage.py startapp demo

# URL mapping
## Write view in view.py

from django.http import HttpResponse

def index(request):
  return HttpResponse("testing string.")

## Map view to URL in urls.py

URL 맵핑은 각 app 에서 하지만
root 의 urls.py 에서 include 해줘야 함.

app 내에선 from . import views 로 가져와서 (index 라고 가정)
path("", views.index, name="index")
를 urlpatterns list 에 넣어 맵핑 해 줌.

# Admin configuration

