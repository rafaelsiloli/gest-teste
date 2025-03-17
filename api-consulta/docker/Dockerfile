FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/seu-projeto.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]