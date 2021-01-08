FROM openjdk:11
COPY ./target/invoice-creator-service-0.0.1-SNAPSHOT.jar invoice-creator-service-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","invoice-creator-service-0.0.1-SNAPSHOT.jar", "--spring.config.name=docker"]