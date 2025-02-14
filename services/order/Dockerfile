# Stage 1: Build the JAR
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Copy only the required files for dependency resolution
COPY pom.xml ./
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests


#ENTRYPOINT ["ls","-l","/app"]

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

#adding curl for healthcheck
RUN apt-get update && apt-get install -y curl

# Copy the JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#ENTRYPOINT ["ls","-l","/app"]
