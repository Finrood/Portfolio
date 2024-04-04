# Use AdoptOpenJDK 21 as the base image
FROM amazoncorretto:21-alpine

# Install Maven
RUN apk add --no-cache maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file to the container
COPY pom.xml .

# Download dependencies if pom.xml changed
RUN mvn dependency:go-offline -B

# Copy the whole project
COPY . .

# Build the project
RUN mvn clean install -DskipTests

# Expose the port that the Spring Boot application will run on
EXPOSE 8091

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "target/Portfolio-1.0.0.jar"]
