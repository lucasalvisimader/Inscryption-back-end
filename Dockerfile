# Etapa de build
FROM maven:3.8.2-openjdk-17-slim AS build
# Copia o código fonte para dentro da imagem
COPY ./src /app/src
COPY ./pom.xml /app/pom.xml
# Define o diretório de trabalho
WORKDIR /app
# Compila o projeto e empacota o jar
RUN mvn clean package -DskipTests
# Etapa final
FROM openjdk:17-jdk-slim
# Copia o jar do build para a imagem final
COPY --from=build /app/target/*.jar app.jar
# Exponha a porta 8083
EXPOSE 8083
# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
