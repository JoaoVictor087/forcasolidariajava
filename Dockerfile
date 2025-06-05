# Stage 1: Build the application using Maven and JDK 21
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml to leverage Docker cache for dependencies
COPY pom.xml .
# Download dependencies
# Using go-offline first can speed up subsequent builds if dependencies haven't changed
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Package the application (this will also compile and run tests)
# The result will be a runnable JAR in target/quarkus-app/quarkus-run.jar
RUN mvn package -B

# Stage 2: Create the runtime image using a JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the executable JAR from the builder stage
COPY --from=builder /app/target/quarkus-app/quarkus-run.jar .

# Your application uses an 'enviromentfile.env' to load database configurations.
# This file is expected to be in the root of the application.
# For Docker, it's often better to manage such configurations using Docker environment variables
# (e.g., via `docker run -e DATABASE_USER=... -e DATABASE_PASSWORD=... -e DATABASE_URL=...`)
# However, to match your current setup where DatabaseConfig.java reads this file,
# you'll need to ensure this file is present in the Docker image or mounted at runtime.
# If you include it directly in the image, be cautious with sensitive data.
#
# Option 1: Copy the file into the image (ensure it's in the Docker build context)
COPY enviromentfile.env .
#
# Option 2 (Recommended for flexibility and security):
# Do not copy 'enviromentfile.env' here. Instead, provide it when running the container,
# for example, by mounting it as a volume:
# `docker run -v ./path/to/your/enviromentfile.env:/app/enviromentfile.env ...`
# Or, modify DatabaseConfig.java to primarily read from system environment variables,
# falling back to the .env file if necessary.

# Expose the port the application runs on (default for Quarkus is 8080)
# As seen in your README.md and application.properties
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]