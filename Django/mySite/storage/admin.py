from django.contrib import admin

# Register your models here.

from .models import TestData

admin.site.register(TestData)