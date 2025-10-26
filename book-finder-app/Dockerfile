# Stage 1: Build the project using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Run Maven to build the project and create a JAR file
RUN mvn clean package

# Stage 2: Run the app using a lightweight Java runtime
FROM eclipse-temurin:17-jre

# Set working directory for runtime
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app listens on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]
