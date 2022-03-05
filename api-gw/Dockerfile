FROM openjdk:8-jdk-alpine
LABEL maintainer="AnujaKanade"
ADD target/api-gw-0.0.1-SNAPSHOT.jar springapi-docker-project.jar
ENTRYPOINT ["java", "-jar", "springapi-docker-project.jar"]
