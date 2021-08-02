FROM openjdk:13-jdk-alpine3.10
ADD target/classicalregistry-0.0.1-SNAPSHOT.jar classicalregistry-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "classicalregistry-0.0.1-SNAPSHOT.jar"]