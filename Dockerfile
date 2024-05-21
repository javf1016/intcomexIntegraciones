# Usa una imagen base de Maven para compilar tu aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente de tu aplicación y compílala
COPY src ./src
RUN mvn package -DskipTests

# Usa una imagen base más ligera para la ejecución
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR compilado desde la imagen de compilación
COPY --from=build /app/target/intcomexIntegraciones-0.0.1-SNAPSHOT.jar /app/intcomexIntegraciones-0.0.1-SNAPSHOT.jar

# Expone el puerto que tu aplicación usará
ENV PORT 8080

# Define el comando de arranque
ENTRYPOINT ["java", "-jar", "intcomexIntegraciones-0.0.1-SNAPSHOT.jar"]
