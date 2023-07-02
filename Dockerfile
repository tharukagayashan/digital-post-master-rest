FROM openjdk:8

RUN mvn clean
RUN mvn install

COPY target/post-master-rest.jar post-master-rest.jar

ENTRYPOINT ["java","-jar","/post-master-rest.jar"]