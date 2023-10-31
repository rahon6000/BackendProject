# Initialize
## venv 만들기
python -m venv djangoVenv

## venv activate
djangoVenv\Scripts\activate.bat
or
djangoVenv\Scripts\Ativate.ps1

## venv check
(djangoVenv) 있어야 함

## Export requirements

pip freeze > requirements.txt

then

pip install -r requirements.txt

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

# Create template

## TEMPLATE_DIR setting

주의! **디폴트로 설정 안되어 있음.**
setting.py 에서
```python
import os
TEMPLATE_DIR = os.path.join(BASE_DIR, 'templates')
TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [TEMPLATE_DIR],
        ...
    }
]
```

해줘야 함.

## 정적 자원 명시

```python
STATIC_URL = 'static/' # app 별로 static 폴더에 있는 것 사용


STATICFILES_DIRS = [  # 프로젝트 전체의 base directory 명시
    BASE_DIR / 'static',
]
```

생각보다 처음 주어지는 셋팅에 구멍이 많아...

bootstrap 같은거 가져와도 되는데, django 전용 템플릿 아니면 수정을 좀 가해야 함.

```html
{% load static %}
        <link href="{% static 'css/styles.css'%}" rel="stylesheet" />
                <script src="{% static 'js/scripts.js'%}"></script>

... 그리고 href 링크 수정
```

## 404 page 띄우기

setting.py 에서 DEBUG=True 인 경우 traceback 이 뜬다. (개발 시 사용)


## Trying Tensorflow

Python 3.9 까지만 지원해서 다운그레이드 함...