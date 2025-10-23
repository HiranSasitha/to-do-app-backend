#  Build the WAR 
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#  Run with JDK 17 
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/CoverageX-0.0.1-SNAPSHOT.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
