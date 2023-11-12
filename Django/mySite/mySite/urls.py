"""
URL configuration for mySite project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from . import views

basePath = "django/"

urlpatterns = [
    # /
    # path("", views.landing, name="landing"),
    
    # django/
    path(basePath, views.index, name="index"),
    path(basePath + "401/", views._401, name="401"),
    path(basePath + "500/", views._500, name="500"),
    
    # ML related
    # path(basePath + "mlPractice/", views.mlPractice, name="mlPractice"),
    path(basePath + 'ml/', include('ml.urls')),
    
    # Form practice
    path(basePath + "form/", views.form, name="form"),
    path(basePath + "form/submit", views.formSubmit, name="formSubmit"),
    
    # Pi playground
    path(basePath + "pi/", views.PiPlay.as_view(), name="pi"),
    
    path(basePath + "layoutStatic/", views.layoutStatic, name="layoutStatic"),    
    path(basePath + "layoutSidenavLight/", views.layoutSidenavLight, name="layoutSidenavLight"),
    path(basePath + "login/", views.login, name="login"),
    path(basePath + "register/", views.register, name="register"),
    path(basePath + "password/", views.password, name="password"),
    path(basePath + "charts/", views.charts, name="charts"),
    path(basePath + "tables/", views.tables, name="tables"),
    
    # django/admin/
    path(basePath + 'admin/', admin.site.urls),
    
    # include django/api/
    path(basePath +'api-auth/', include('rest_framework.urls')),
    path(basePath + 'api/', include('apis.urls')),
]