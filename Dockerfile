FROM maven:3.8.1-openjdk-16
COPY ./ ./
RUN mvn clean package
ARG JAR_FILE=target/luis-assessment-crawler-1.0-SNAPSHOT-jar-with-dependencies.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]