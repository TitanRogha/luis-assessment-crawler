FROM openjdk:16-jdk-alpine
ARG JAR_FILE=target/luis-assessment-crawler-1.0-SNAPSHOT-jar-with-dependencies.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]