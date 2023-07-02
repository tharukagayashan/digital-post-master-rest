#
#Build stage
#
FROM maven:3.8.2-jdk-8 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:8
COPY --from=build /target/post-master-rest.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]