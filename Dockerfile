FROM openjdk:17-jdk-alpine
MAINTAINER sltn011
COPY ./Lab1CICD/target/Lab1CICD-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar","./pastebox.jar"]
