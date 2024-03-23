FROM maven:3.8-openjdk-17 as builder
COPY pom.xml .
COPY src src
RUN --mount=type=cache mvn clean package -DskipTests

FROM openjdk
COPY target/olimp-3.2.4.jar olimp.jar
CMD ["java", "-jar", "olimp.jar"]