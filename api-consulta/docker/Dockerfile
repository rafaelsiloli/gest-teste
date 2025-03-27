FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/api-consulta-0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
