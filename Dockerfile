FROM sapmachine:21.0.1-jdk-ubuntu AS builder

WORKDIR /build

COPY . .
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

FROM sapmachine:21.0.1-jdk-ubuntu

ENV SPRING_PROFILES_ACTIVE=prod

WORKDIR /opt/app
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
