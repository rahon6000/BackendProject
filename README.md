# BackendProject

Cheatsheet project for backend servers!

# What are thoese folders...?
- Django : Django practice, just a good look at its structure.
- Spring webflux : Trying webflux with netty.
- zold_Spring boot : Old remains of spring boot practice.
- Spring boot : (Yet again) Clean slate of spring boot with Bootstrap.

# Django

Initial setting for Django apps
- Use markdown (markdownify)
- Use system environments
  - `MY_HOST` : Host name of your db ('localhost' for dev.)
  - `MY_DB` : Database name (schema name)
  - `IS_DEBUG` : Set debug mode. Default is 'False' if there is no such variables.
  - 
- 

# Docker

- There is issue with Tensorflow installation. (in Alpine linux)


## using docker commands manually

### local

images not in my hand

```shell
docker network create mynet                   # make network between containers
docker volume create mysql-data               # make volume used by DB
docker run -d --name db -p 3307:3306 --network mynet -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=db_server mysql
```

images in my hand

```shell
docker build --pull --rm -f "Django\Dockerfile" -t djangoapp "Django"
docker run -d --name server -p 80:80 --network mynet -e MY_DB=db_server -e MY_HOST=db djangoapp


docker build --pull --rm -f "zold_Spring boot\Dockerfile" -t springapp "zold_Spring boot"
docker run -d --name springapp -p 8080:8080 --network mynet -e MY_DB=db_server -e MY_HOST=db springapp

```

### remote

Push to Google

```shell
# test
docker build --pull --rm -f "Django\Dockerfile" -t gcr.io/supple-hangout-398705/djangoapp "Django"
docker run -d --name server -p 80:80 --network mynet -e MY_DB=db_server -e MY_HOST=db gcr.io/supple-hangout-398705/djangoapp

# push
gcloud init
gcloud auth login
gcloud auth configure-docker gcr.io

docker push gcr.io/supple-hangout-398705/djangoapp
docker push gcr.io/supple-hangout-398705/springapp
docker push gcr.io/supple-hangout-398705/mysql
```


Push at Compute engine SSH

install docker engine in debian

```shell
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/debian/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

echo \
  "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
  "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

Pull images from Artifacts repo

```shell
sudo gcloud auth configure-docker gcr.io

sudo docker pull gcr.io/supple-hangout-398705/springapp
sudo docker pull gcr.io/supple-hangout-398705/djangoapp
sudo docker pull gcr.io/supple-hangout-398705/mysql
```

Run images (container)

```shell
sudo docker network create mynet
sudo docker volume create mysql-data

sudo docker run -d --name db -p 3306:3306 --network mynet -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=db_server gcr.io/supple-hangout-398705/mysql

sudo docker run -d --name springapp -p 8080:8080 --network mynet -e MY_DB=db_server -e MY_HOST=db -e IS_DEBUG=False gcr.io/supple-hangout-398705/springapp
sudo docker run -d --name server -p 80:80 --network mynet -e MY_DB=db_server -e MY_HOST=db -e IS_DEBUG=False gcr.io/supple-hangout-398705/djangoapp
```

Rerun container

```shell
sudo docker container rm -f server
sudo docker run -d --name server -p 80:80 --network mynet -e MY_DB=db_server -e MY_HOST=db -e IS_DEBUG=False gcr.io/supple-hangout-398705/djangoapp
```

# Refactor Alpine -> Ubunto 

뭔가 좀 다른 nginx 가 설치됨.

Alpine:3.15 버전 nginx/1.20.2 의 nginx.conf
```
user nginx;
workder_processes auto;
pcre_jit on;
error-log /var/log/nginx/error.log warn;
include /etc/nginx/modules/*.conf;
# include /etc/nginx/conf.d/*conf;

events {
	worker_connections 1024;
}

http {
	include /etc/nginx/mime.types;
	default_type application/octet-stream;

	server_tokens off;

	client_max_body_size 1m;

	sendfile on;

	tcp_nopush on;

	ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
	ssl_prefer_server_ciphers on;
	ssl_session_cache shared:SSL:2m;
	ssl_session_timeout 1h;
	ssl_session_tickets off;

	gzip_vary on;
	map $http_upgrade $connection_upgrade {
		default upgrade;
		'' close;
	}

	access_log /var/log/nginx/access.log main;

	include /etc/nginx/http.d/*.conf;
}
```


Ubuntu 버전 nginx/1.18.0 (Ubuntu) 의 nginx.conf
```
user www-data;
worker_processes auto;
pid /run/nginx.pid;
include /etc/nginx/modules-enabled/*.conf;

events {
	worker_connections 768;
}

http {
	sendfile on;
	tcp_nopush on;
	types_hash_max_size 2048;

	include /etc/nginx/mime.types;
	default_type application/octet-stream;

	ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
	ssl_prefer_server_ciphers on;

	access_log /var...
	error_log /var...

	gzip on;

	include /etc/nginx/conf.d/*.conf;
	include /etc/nginx/sites-enabled/*;
}
```