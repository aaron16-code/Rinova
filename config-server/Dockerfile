# 1 CONSTRUCCION
FROM maven:3.9.11-eclipse-temurin-25 AS build
WORKDIR /app

COPY config-server/.mvn/ .mvn/
COPY config-server/mvnw mvnw
COPY config-server/pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY config-server/src ./src
RUN ./mvnw clean package -DskipTests

# 2 EJECUCION
FROM eclipse-temurin:25-jre
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY config-microservicios ./config-microservicios
ENTRYPOINT ["java","-jar","app.jar"]
