# BackendProject

Cheatsheet project for backend servers!

# Docker

## using docker commands manually

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

```