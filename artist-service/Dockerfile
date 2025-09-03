# Use a Maven image to build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/artist-service-1.0.0.jar app.jar

# Service port (artist-service runs on 8081)
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
