FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN ls -l /app/target # Debugging: Zeige den Inhalt des target-Verzeichnisses an

FROM openjdk:21-jdk-slim
WORKDIR /backend
COPY --from=build /app/target/sum-analyzer.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
