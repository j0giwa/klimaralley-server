FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .
COPY target/klimaralley.server-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar"]
