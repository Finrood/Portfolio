# Build stage
FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /app

# Copy the parent POM first
COPY pom.xml .

# Copy the backend and frontend module's POMs
COPY server/pom.xml server/

# Copy the rest of the project
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Production stage
FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=build /app/server/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
