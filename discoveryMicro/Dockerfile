FROM eclipse-temurin:22-jre-jammy

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=prod -jar /app.jar"]
HEALTHCHECK --start-period=60s --interval=15s --timeout=5s --retries=3 \
  CMD curl -f http://localhost:8761/actuator/health || exit 1