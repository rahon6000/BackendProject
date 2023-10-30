"""
Django settings for mySite project.

Generated by 'django-admin startproject' using Django 4.2.6.

For more information on this file, see
https://docs.djangoproject.com/en/4.2/topics/settings/

For the full list of settings and their values, see
https://docs.djangoproject.com/en/4.2/ref/settings/
"""

from pathlib import Path
import os

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent.parent
TEMPLATE_DIR = os.path.join(BASE_DIR, 'templates')


# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/4.2/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'django-insecure-ctq20(e@1chl=n4x2^3u)+kr3jkd^n4ga#d(uhnap9)*m6=^u+'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

# A list of strings representing the host/domain names that this Django site can serve.
# This is a security measure to prevent HTTP Host header attacks, which are possible even under many seemingly-safe web server configurations.
# Values in this list can be fully qualified names (e.g. 'www.example.com'), in which case they will be matched against the request’s Host header exactly (case-insensitive, not including port). 
# A value beginning with a period can be used as a subdomain wildcard: '.example.com' will match example.com, www.example.com, and any other subdomain of example.com. 
# A value of '*' will match anything; in this case you are responsible to provide your own validation of the Host header (perhaps in a middleware; if so this middleware must be listed first in MIDDLEWARE).
# Django also allows the fully qualified domain name (FQDN) of any entries. Some browsers include a trailing dot in the Host header which Django strips when performing host validation.
# If the Host header (or X-Forwarded-Host if USE_X_FORWARDED_HOST is enabled) does not match any value in this list, the django.http.HttpRequest.get_host() method will raise SuspiciousOperation.
# When DEBUG is True and ALLOWED_HOSTS is empty, the host is validated against ['.localhost', '127.0.0.1', '[::1]'].
# ALLOWED_HOSTS is also checked when running tests.
# This validation only applies via get_host(); if your code accesses the Host header directly from request.META you are bypassing this security protection.
# ALLOWED_HOSTS = [] # DEBUG True
ALLOWED_HOSTS = ['localhost']


# Application definition

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'markdownify.apps.MarkdownifyConfig', # CUSTOM
]

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = 'mySite.urls'

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [TEMPLATE_DIR],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]

WSGI_APPLICATION = 'mySite.wsgi.application'


# Database
# https://docs.djangoproject.com/en/4.2/ref/settings/#databases

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'db_server',
        'USER': 'springuser',
        'PASSWORD': '1234',
        'HOST': 'localhost',
        'PORT': '3306',
        }
    }


# Password validation
# https://docs.djangoproject.com/en/4.2/ref/settings/#auth-password-validators

AUTH_PASSWORD_VALIDATORS = [
    {
        'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
    },
]


# Internationalization
# https://docs.djangoproject.com/en/4.2/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/4.2/howto/static-files/

STATIC_URL = 'static/'
STATICFILES_DIRS = [
    BASE_DIR / 'static',
]

# Markdownify
# refer : https://django-markdownify.readthedocs.io/en/latest/settings.html
MARKDOWNIFY = {
    "default": {
        "WHITELIST_TAGS": [
            'h1', 'h2', 'h3', 'h4',
            'br',
            'code',
            'a',
            'abbr',
            'acronym',
            'b',
            'blockquote',
            'em',
            'i',
            'li',
            'ol',
            'p',
            'strong',
            'ul'
        ],
        "BLEACH": False,
        "MARKDOWN_EXTENSIONS": [
            "fenced_code",
            "codehilite",
        ]
    }
}

# Default primary key field type
# https://docs.djangoproject.com/en/4.2/ref/settings/#default-auto-field

DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'
