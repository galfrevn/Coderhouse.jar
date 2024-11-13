# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.8.1-openjdk-17-slim AS builder

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Ejecutar Maven para construir el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación con OpenJDK
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación escucha (por defecto Spring Boot usa el puerto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
