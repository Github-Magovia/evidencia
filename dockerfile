FROM openjdk:17-jdk-slim-buster
COPY target/evidencia-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]