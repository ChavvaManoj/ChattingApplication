# Use official OpenJDK 17 image as base
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy everything from your local project to the container
COPY . .

# Build the Spring Boot app (using Maven wrapper if it exists)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the generated jar file
CMD ["java", "-jar", "target/ChattingApp-0.0.1-SNAPSHOT.jar"]
