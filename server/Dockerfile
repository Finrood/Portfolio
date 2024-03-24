# Use AdoptOpenJDK 21 as the base image
FROM amazoncorretto:21-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/Portfolio-1.0.0.jar /app/Portfolio.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8091

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "Portfolio.jar"]
