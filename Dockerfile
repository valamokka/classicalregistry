FROM openjdk:11.0.4-jre-slim-buster
ADD target/classicalregistry-0.0.1-SNAPSHOT.jar classicalregistry-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "classicalregistry-0.0.1-SNAPSHOT.jar"]