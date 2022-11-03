FROM openjdk:17-jdk-slim

COPY ["eureka/target/eureka-1.0-SNAPSHOT.jar", "eureka.jar"]
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka.jar"]