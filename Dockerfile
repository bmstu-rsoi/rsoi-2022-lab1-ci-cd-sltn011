FROM openjdk:17-jdk-alpine
MAINTAINER sltn011
EXPOSE 8080
COPY Lab1CICD/target/Lab1CICD-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar", "-Dserver.port=$PORT","./pastebox.jar"]