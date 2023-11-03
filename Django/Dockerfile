# Copy pasted from https://pyrasis.com/jHLsAlwaysUpToDateDocker/Unit11/02
# Using alpine image
FROM alpine:latest

# Time zone setting for alpine (it is not general)
RUN apk add tzdata
ENV TZ=Asia/Seoul



# Python, Pip, mysqlclient, nginx (server), gcc (run c)
RUN apk add python3 py3-pip py3-mysqlclient nginx gcc

# Nginx 가 앞단에서 실행하도록 .conf 설정
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
ADD default.conf /etc/nginx/http.d/default.conf

# pip 으로 라이브러리 설치
RUN pip install django gunicorn mysqlclient django-markdownify
# RUN pip install -r requirements.txt

# Linux dir 에 디렉터리 추가.
ADD ./ /var/www/mysite
WORKDIR /var/www/mysite
# 엔트리포인트 권한 설정.
# RUN chmod +x entrypoint.sh

ADD entrypoint.sh ./

# This is need if .sh is written in windows... (why it was OK in other windows anyway...?)
RUN sed -i -e 's/\r$//' entrypoint.sh

# 포트넘버
EXPOSE 80

ENTRYPOINT ./entrypoint.sh
