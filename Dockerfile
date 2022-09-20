FROM openjdk:17-jdk-alpine
MAINTAINER sltn011
RUN pwd
RUN ls
RUN mvn -f ./Lab1CICD/pom.xml --batch-mode --update-snapshots package -Dmaven.test.skip=true
COPY ./Lab1CICD/target/Lab1CICD-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar","./pastebox.jar"]
