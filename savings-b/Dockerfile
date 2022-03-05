FROM openjdk:8-jdk-alpine
LABEL maintainer="AnujaKanade"
ADD target/savings-a-0.0.1-SNAPSHOT.jar savingsa-microservice-docker.jar
ENTRYPOINT ["java", "-jar", "savingsa-microservice-docker.jar"]