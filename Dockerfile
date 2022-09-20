FROM openjdk:17-jdk-alpine
COPY Lab1CICD/src /home/app/src
COPY Lab1CICD/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
COPY /home/app/target/Lab1CICD-0.0.1-SNAPSHOT.jar /usr/local/lib/pastebox.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/pastebox.jar"]
