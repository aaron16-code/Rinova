# 1 CONSTRUCCION
FROM maven:3.9.11-eclipse-temurin-25 AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests

# 2 EJECUCION
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# El config-server usa perfil "native" y lee los .properties desde ./config-microservicios
# (ruta relativa al working dir, ver application.properties)
COPY config-microservicios ./config-microservicios
ENTRYPOINT ["java","-jar","app.jar"]
