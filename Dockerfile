FROM openjdk:17
WORKDIR /app
COPY target/Yr4-Lab2-Building-Two-Interacting-Microservices-2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]