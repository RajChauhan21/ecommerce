FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

#  your JAR filename
CMD ["java", "-jar", "target/E-Commerce-0.0.1-SNAPSHOT.jar"]