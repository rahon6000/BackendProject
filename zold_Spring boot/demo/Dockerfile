# 첫 번째 컨테이너 만들기 (빌더 컨테이너)
FROM adoptopenjdk/openjdk11:latest as builder

WORKDIR application

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

# 두 번째 컨테이너 만들기
FROM adoptopenjdk/openjdk11:latest

WORKDIR application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher" ]