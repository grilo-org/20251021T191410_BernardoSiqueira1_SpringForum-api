# Construir a aplicacao
FROM maven:latest AS builder

WORKDIR /forumapi
COPY . .
RUN mvn clean package -DskipTests

# Executar .JAR
FROM openjdk:21-oracle
WORKDIR /forumapi
COPY --from=builder /forumapi/target/*.jar forum.jar

CMD ["java", "-jar", "forum.jar"]

