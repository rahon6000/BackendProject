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

Push to Google

```shell
gcloud init
docker push gcr.io/supple-hangout-398705/djangoapp
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

sudo docker pull gcr.io/supple-hangout-398705/djangoapp
sudo docker pull gcr.io/supple-hangout-398705/mysql
```

Run images

```shell
sudo docker network create mynet
sudo docker volume create mysql-data

sudo docker run -d --name db -p 3307:3306 --network mynet -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=db_server gcr.io/supple-hangout-398705/mysql

sudo docker run -d --name server -p 80:80 --network mynet -e MY_DB=db_server -e MY_HOST=db gcr.io/supple-hangout-398705/djangoapp
```