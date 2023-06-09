FROM openjdk:latest

WORKDIR /usr/app

ENV SPRING_PROFILES_ACTIVE=docker

COPY ./target/ginkano-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "ginkano-0.0.1-SNAPSHOT.jar"]