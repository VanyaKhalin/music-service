FROM eclipse-temurin:25-jre
WORKDIR /app
COPY /build/libs/music-service-0.0.1-SNAPSHOT.jar /app/music-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "music-service.jar"]