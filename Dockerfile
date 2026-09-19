# STAGE 1
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle/wrapper ./gradle/wrapper
COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY src ./src

RUN chmod +x gradlew && ./gradlew clean build -x test

# STAGE 2
FROM eclipse-temurin:21-jre

COPY --from=builder app/build/libs/*.jar app.jar

RUN useradd -r -s /bin/bash spring

EXPOSE 8080

USER spring

ENTRYPOINT ["java", "-jar", "app.jar"]
