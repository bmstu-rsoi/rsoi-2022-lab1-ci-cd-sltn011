FROM openjdk:17-jdk-alpine
MAINTAINER sltn011
RUN mvn -f ./Lab1CICD/pom.xml -Dmaven.test.skip=true --batch-mode --update-snapshots package
COPY ./Lab1CICD/target/Lab1CICD-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar","./pastebox.jar"]
