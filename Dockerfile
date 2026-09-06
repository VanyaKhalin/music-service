FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN chmod +x gradlew
COPY src ./src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/build/libs/music-service-0.0.1-SNAPSHOT.jar ./music-service.jar
ENTRYPOINT ["java", "-jar", "music-service.jar"]