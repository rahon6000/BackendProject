from rest_framework import serializers
from .models import ColorScore

class ColorScoreSerializer(serializers.ModelSerializer):
  class Meta:
    model = ColorScore
    fields = (
      'answerColorR',
      'answerColorG',
      'answerColorB',
      'queryColorR',
      'queryColorG',
      'queryColorB',
      'score',
      'elapse',
    )