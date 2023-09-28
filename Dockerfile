FROM openjdk:17-jre-slim
WORKDIR /app
COPY ./build/libs/grupplabb3-0.0.1-SNAPSHOT.jar /app/grupplabb3.jar
ENTRYPOINT ["java", "-jar", "my-app.jar"]
EXPOSE 8080
