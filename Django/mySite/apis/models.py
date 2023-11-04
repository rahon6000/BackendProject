from django.db import models
from datetime import datetime

# Create your models here.

class Count(models.Model):
  date = models.DateField(primary_key=True, default=datetime.today().strftime("%Y-%m-%d"))
  total = models.IntegerField(default = 0, blank=False, null=False)
  today = models.IntegerField(default = 0, blank=False, null=False)
  