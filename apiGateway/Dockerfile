FROM eclipse-temurin:22-jdk-jammy

RUN apt-get update && \
    apt-get install -y curl maven && \
    rm -rf /var/lib/apt/lists/*

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=prod -jar /app.jar"]

HEALTHCHECK --interval=15s --timeout=3s --start-period=30s \
  CMD curl -f http://localhost:8083/actuator/health || exit 1