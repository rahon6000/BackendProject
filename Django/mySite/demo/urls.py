from django.urls import path
from . import views #from this module

urlpatterns = [
    path("", views.index, name="index"),
    
]
