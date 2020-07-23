FROM maven:3-jdk-8-alpine as builder
ADD ./pom.xml /tmp
ADD src/ /tmp/src/
WORKDIR /tmp
RUN mvn package
FROM openjdk:8-jdk-alpine
COPY --from=builder /tmp/target/monitor-dashboard-0.0.1-SNAPSHOT.jar /tmp/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tmp/monitor-dashboard-0.0.1-SNAPSHOT.jar"]
