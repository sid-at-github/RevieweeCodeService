FROM openjdk:8-jre-alpine
COPY target/RevieweeCodeService-1.0.jar /RevieweeCodeService-1.0.jar
CMD ["/usr/bin/java", "-jar", "/RevieweeCodeService-1.0.jar"]
