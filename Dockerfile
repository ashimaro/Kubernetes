FROM openjdk:8

EXPOSE 8080
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar

ADD target/SpringBootMicroservicesContainerization-0.0.1-SNAPSHOT.jar SpringBootMicroservicesContainerization-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "SpringBootMicroservicesContainerization-0.0.1-SNAPSHOT.jar"]