# Copy pasted from https://pyrasis.com/jHLsAlwaysUpToDateDocker/Unit11/02
# Using alpine image (use old version for old python for tensorflow)
FROM alpine:3.15
# FROM ubuntu:latest

# Time zone setting for alpine (it is not general)
RUN apk add tzdata
ENV TZ=Asia/Seoul

# Python, Pip, mysqlclient, nginx (server), gcc (run c)
# Alpine (light weight, but no tensorflow I guess)
RUN apk add python3 py3-pip py3-mysqlclient nginx gcc

# Ubuntu (official support for tensorflow...)
#RUN apt-get install python3 python3-pip -y
#RUN pip3 install django gunicorn mysqlclient django-markdownify djangorestframework
#RUN pip3 install tensorflow

# Nginx 가 앞단에서 실행하도록 .conf 설정
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
ADD default.conf /etc/nginx/http.d/default.conf

# pip 으로 라이브러리 설치
# requirement 는 환경 dependence 있으므로 주의
RUN pip install django gunicorn mysqlclient django-markdownify djangorestframework
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
