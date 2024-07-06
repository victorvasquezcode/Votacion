# Usa una imagen base de JDK
FROM openjdk:17-jdk-slim

# Instala Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias necesarias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copia el resto de los archivos del proyecto
COPY . .

# Construye la aplicación
RUN mvn clean install -DskipTests

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "target/votacionsegura-0.0.1-SNAPSHOT.jar"]
