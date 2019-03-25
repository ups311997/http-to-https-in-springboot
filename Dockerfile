FROM openjdk:11
ADD ./target/spring-redis-example-0.0.1-SNAPSHOT.jar /usr/src/spring-redis-example-0.0.1-SNAPSHOT.jar
ADD ./keystore.p12 /usr/src/keystore.p12
WORKDIR usr/src
EXPOSE 8082
ENTRYPOINT ["java","-jar", "spring-redis-example-0.0.1-SNAPSHOT.jar"]