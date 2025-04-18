# Stage 1: Build with Maven
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app
COPY . .

# Build the JAR (standard Spring Boot executable)
RUN chmod +x ./mvnw && ./mvnw clean package -pl initializr-service-sample -am -DskipTests
# RUN cd initializr-service-sample && ../mvnw clean package

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre

WORKDIR /app
# Copy only the built JAR (not the sources JAR)
COPY --from=builder /app/initializr-service-sample/target/*.jar app.jar

# Spring Boot will default to 8080, but we expose explicitly
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]