cd mysite

python3 manage.py makemigrations
python3 manage.py migrate

gunicorn mysite.wsgi -b :8000 -D # Daemon... 으로 안하면 안돌아감.
# gunicorn mysite.wsgi -b unix:/tmp/gunicorn.sock -D # Daemon... 으로 안하면 안돌아감.
nginx