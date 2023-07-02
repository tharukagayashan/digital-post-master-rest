FROM openjdk:8

WORKDIR /app

COPY pom.xml .

COPY src ./src
COPY .mvn ./mvn

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

WORKDIR /app

COPY target/post-master-rest.jar /app/post-master-rest.jar

ENTRYPOINT ["java","-jar","/app/post-master-rest.jar"]