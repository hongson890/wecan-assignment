FROM openjdk:8
ADD target/sonny-wecan-assignment-1.0.jar sonny-wecan-assignment-1.0.jar
ENTRYPOINT ["java", "-jar","sonny-wecan-assignment-1.0.jar"]
EXPOSE 8000
