FROM openjdk:8

COPY target/digital-post-master-rest-0.0.1-SNAPSHOT.jar post-master-rest.jar

ENTRYPOINT ["java","-jar","/post-master-rest.jar"]