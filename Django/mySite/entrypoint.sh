# Commands run at container start.

python3 manage.py migrate
gunicorn mySite.wsgi -b unix:/tmp/gunicorn.sock -D # Daemon
nginx