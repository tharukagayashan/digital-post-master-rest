FROM openjdk:8

COPY pom.xml .

COPY src ./src
COPY .mvn ./.mvn

ARG JAR_FILE=target/*.jar

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

COPY ./target/post-master-rest.jar post-master-rest.jar

ENTRYPOINT ["java","-jar","/post-master-rest.jar"]