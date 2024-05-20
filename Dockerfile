FROM openjdk:17-jdk-slim
COPY target/intcomexIntegraciones-0.0.1-SNAPSHOT.jar /intcomexIntegraciones-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/intcomexIntegraciones-0.0.1-SNAPSHOT.jar"]
