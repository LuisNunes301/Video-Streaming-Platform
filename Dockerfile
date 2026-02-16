# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instalar FFmpeg
RUN apk add --no-cache ffmpeg

# Diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado pelo Maven
COPY target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
