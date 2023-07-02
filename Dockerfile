# Use an OpenJDK 8 base image
FROM openjdk:8

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src
COPY .mvn ./mvn

# Install Maven and build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean install

# Copy the built JAR file to the container
COPY target/post-master-rest.jar /app/app.jar

# Set the entrypoint command to run the Spring Boot application
ENTRYPOINT ["java","-jar","/app/app.jar"]
