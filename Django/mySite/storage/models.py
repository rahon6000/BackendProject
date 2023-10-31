from typing import Any
from django.db import models
from datetime import datetime

# Create your models here.


class TestData(models.Model):
    """테스트 엔티티."""

    testData_id = models.AutoField(primary_key=True)
    testData_name = models.CharField(max_length=100, default='placeholder')
    testData_position = models.CharField(max_length=100, default='undecided')
    testData_office = models.CharField(max_length=100, default='undecided')
    testData_age = models.IntegerField(default=0, blank=False, null=False)
    testData_startDate = models.DateField(
        default=datetime.today().strftime("%Y-%m-%d"), blank=False, null=False
    )
    testData_salary = models.IntegerField(default=1)
