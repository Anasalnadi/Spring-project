FROM ubuntu:latest
LABEL authors="nadon"

ENTRYPOINT ["top", "-b"]
FROM adoptopenjdk/openjdk17:alpine

WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]