# Using ubuntu for tensorflow
# FROM ubuntu:22.04 (change the tag for later error)
FROM ubuntu:latest

# Python, Pip, mysqlclient, nginx (server), gcc (run c)

# Ubuntu (official support for tensorflow...)
RUN apt update
RUN apt-get install python3 python3-pip -y
RUN apt-get install default-libmysqlclient-dev -y
RUN apt-get install nginx -y
RUN pip3 install django gunicorn mysqlclient==2.0.3 django-markdownify djangorestframework
RUN pip3 install pillow
RUN pip3 install tensorflow

# For Debug
# RUN apt-get install nano net-tools -y

# Nginx 가 앞단에서 실행하도록 .conf 설정. Override 'default' file
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
ADD default_ubuntu.conf /etc/nginx/sites-available/default
ADD default_ubuntu.conf /etc/nginx/sites-enabled/default

# pip 으로 라이브러리 설치
# requirement 는 환경 dependence 있으므로 주의
# Linux dir 에 디렉터리 추가.
# 엔트리포인트 권한 설정.
# RUN chmod +x entrypoint.sh
ADD ./ /var/www/mysite
WORKDIR /var/www/mysite

ADD entrypoint.sh ./

# This is need if .sh is written in windows... (why it was OK in other windows anyway...?)
RUN sed -i -e 's/\r$//' entrypoint.sh

# 포트넘버
EXPOSE 80

ENTRYPOINT ./entrypoint.sh