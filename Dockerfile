## Define a imagem base
#FROM maven:3.8.2-openjdk-17-slim AS builder
#
#COPY ./src /app/src
#COPY ./pom.xml /app/pom.xml
#
#WORKDIR /app
#
#RUN mvn clean package -e
#
#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
#COPY --from=builder /app/target/trunfo.jar trunfo.jar
#
#EXPOSE 8080
#
#CMD ["java", "-jar", "trunfo.jar"]

FROM openjdk:17
COPY target/Trunfo-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]
