# Stage 1: Build
FROM maven:latest AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run
FROM openjdk:21-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]
