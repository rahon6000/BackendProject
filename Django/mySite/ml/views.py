from django.http  import HttpRequest, HttpResponse
from django.shortcuts import render, redirect
from django.contrib.staticfiles import finders
from django.views import View
import os
from PIL import Image
from tensorflow import keras
import tensorflow as tf
import numpy as np
import time
# Create your views here.

def index(request:HttpRequest):
  markdownText = open(
    os.path.join(os.path.dirname(__file__), 'markdowns/mlPractice.md')).read()
    
  context = {
    'markdownText': markdownText,
  }
  return render(request, 'mysite/mlIndex.html', context=context)


class CatDog(View):
  markdownText = open(
    os.path.join(os.path.dirname(__file__), 'markdowns/catdog.md')).read()
  
  def get(self, request: HttpRequest):
    img = keras.utils.load_img(
        finders.find('test.jpg'), ## file at static resources
        # os.path.join(os.path.dirname(__file__), 'TFmodels/test.jpg'),
        target_size = (180, 180),
      )
    prediction = TFmodel.predict(img)
    dogP = np.ceil(10000 * prediction[0])/100
    context = {
      'markdownText': CatDog.markdownText,
      'image' : img,
      'catP' : 100 - dogP,
      'dogP' : dogP,
      'elapse' : f'{prediction[1]:.3f}',
      'placeHolder' : "/static/test.jpg",
    }
    return render(request, 'mysite/mlCatDog.html', context=context)
  
  def post(self, request: HttpRequest):
    if not (request.FILES.get('file')):
      return redirect('catdog')
    img = Image.open(request.FILES.get('file')).resize(size = (180,180))
    prediction = TFmodel.predict(img)
    dogP = np.ceil(10000 * prediction[0])/100
    
    print(request.POST.get('imgURL'))
    context = {
      'markdownText': CatDog.markdownText,
      'image' : img,
      'catP' : 100 - dogP,
      'dogP' : dogP,
      'elapse' : f'{prediction[1]:.3f}',
      'placeHolder' : request.POST.get('imgURL'),
    }
    return render(request, 'mysite/mlCatDog.html', context=context)


class TFmodel:
  model_path = os.path.join(os.path.dirname(__file__), 'TFmodels/CatDog.keras')
  model = keras.models.load_model(model_path)
  
  @staticmethod
  def predict(img):
    start_time = time.time() # seconds
    img_array = keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array, 0)
    prediction = TFmodel.model.predict(img_array)
    print(prediction[0,0])
    return prediction[0,0], (time.time() - start_time)