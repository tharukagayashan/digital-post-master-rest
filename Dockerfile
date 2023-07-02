FROM openjdk:8

ARG JAR_FILE=target/*.jar

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

COPY ./target/post-master-rest.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]