# Use Eclipse Temurin JDK 17 slim image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build output (jar file) into container
COPY target/CoverageX-0.0.1-SNAPSHOT.war app.war

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.war"]
