# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -pl backend/transaction-engine -am -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/backend/transaction-engine/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]