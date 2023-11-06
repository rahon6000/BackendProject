from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='mlPractice'),
    path('catdog', views.CatDog.as_view(), name='catdog'),    
]
