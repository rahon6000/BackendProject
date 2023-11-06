from django.db import models
from datetime import datetime
import uuid

# Create your models here.

class Count(models.Model):
  date = models.DateField(primary_key=True, default=datetime.today().strftime("%Y-%m-%d"))
  total = models.IntegerField(default = 0, blank=False, null=False)
  today = models.IntegerField(default = 0, blank=False, null=False)

class ColorScore(models.Model):
  id = models.UUIDField(primary_key=True, default = uuid.uuid4, editable=False)
  answerColorR = models.IntegerField(default = 0, blank=False, null=False)
  answerColorG = models.IntegerField(default = 0, blank=False, null=False)
  answerColorB = models.IntegerField(default = 0, blank=False, null=False)
  queryColorR = models.IntegerField(default = 0, blank=False, null=False)
  queryColorG = models.IntegerField(default = 0, blank=False, null=False)
  queryColorB = models.IntegerField(default = 0, blank=False, null=False)
  score = models.FloatField(default=0, blank=False, null=False)
  elapse = models.IntegerField(default = 0, blank=False, null=False)
  
  @staticmethod
  def average():
    return ColorScore.objects.aggregate(models.Avg('score'))