FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
