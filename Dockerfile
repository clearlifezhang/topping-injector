FROM openjdk:17-slim
WORKDIR /app
COPY target/topping-injector-0.0.1-SNAPSHOT.jar /app/data-injector.jar
EXPOSE 5050
CMD ["java", "-jar", "/app/data-injector.jar"]

