# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests
#RUN curl -f -L \
# -X GET "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.25.1/opentelemetry-javaagent.jar" \
# --output /tmp/opentelemetry-javaagent.jar
COPY opentelemetry-javaagent-1.25.1.jar /tmp/opentelemetry-javaagent.jar

# Package stage
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./myapp.jar
COPY --from=build /tmp/opentelemetry-javaagent.jar /opt/otel/opentelemetry-javaagent.jar
EXPOSE 8080
ENV JAVA_OPTS="-javaagent:/opt/otel/opentelemetry-javaagent.jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8780"
CMD java $JAVA_OPTS -jar myapp.jar