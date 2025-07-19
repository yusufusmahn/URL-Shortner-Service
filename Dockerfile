# 1. Use an official Java runtime as base image
FROM eclipse-temurin:21-jdk-alpine

# 2. Set the working directory in the container
WORKDIR /app

# 3. Copy the built JAR file into the container
COPY target/URLShortnerService-1.0-SNAPSHOT.jar app.jar

# 4. Expose the application port
EXPOSE 8080

# 5. Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
