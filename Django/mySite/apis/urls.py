from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('count/', views.count.as_view()),
    path('resetSessions/', views.ResetSessions.as_view()),
    path('color/', views.ColorScoreAPI.as_view()),
    path('color/getav', views.ColorScoreAPI_average.as_view()),
]
