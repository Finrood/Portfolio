# Dockerfile for portfolio-backend
FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
VOLUME /tmp
COPY --from=build /app/target/*.jar Portfolio-1.0.0.jar
ENTRYPOINT ["java","-jar","/Portfolio-1.0.0.jar"]
