# Use an official Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy your project files into the container
COPY . .

# Build the project and package it into a JAR
RUN mvn clean package

# Use a lightweight Java runtime to run the app
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
