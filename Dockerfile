FROM eclipse-temurin:17
WORKDIR /app
COPY ./build/libs/*.jar my-app.jar
ENTRYPOINT ["java", "-jar", "my-app.jar"]
EXPOSE 8080