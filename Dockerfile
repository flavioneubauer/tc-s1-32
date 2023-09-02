FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY application application
COPY core core
COPY external external

RUN ./mvnw clean install -DskipTests && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/application
COPY --from=build ${DEPENDENCY}/target/*.jar /app/.
ENTRYPOINT ["java","-jar","application-runner.jar"]
