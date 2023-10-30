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


urlpatterns = [
    path("", views.index, name="index"),
    # path("path", views.path, name="path"),
    path("mlPractice", views.mlPractice, name="mlPractice"),
    path("layoutStatic/", views.layoutStatic, name="layoutStatic"),
    path("layoutSidenavLight/", views.layoutSidenavLight, name="layoutSidenavLight"),
    path("login/", views.login, name="login"),
    path("401/", views._401, name="401"),
    # path("404/", views._404, name="404"),
    path("500/", views._500, name="500"),
    path("register/", views.register, name="register"),
    path("password/", views.password, name="password"),
    path("charts/", views.charts, name="charts"),
    path("tables/", views.tables, name="tables"),
    
    path('admin/', admin.site.urls),
    
    # path("demo/", include("demo.urls")),
]