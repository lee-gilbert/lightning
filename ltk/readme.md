# RESTful backend Server impl.

Requires Java v8 (does not work with later versions), mvn v3.5+, for Build.


1a) Compile and build with

    `./mvnw clean install`

1b) Alternatively Compile and build with skipping the integration tests

    `./mvnw clean install -Dskip.integration.tests=true`

2a) Start locally with

    `./mvnw spring-boot:run`

2b) Alternatively Start locally with

    `java -jar ./target/ltk-0.0.1-SNAPSHOT.jar`



## Technical Stack

 1. Spring Boot (2.0.8.RELEASE)
 2. JPA (2.1), Hibernate (5), h2 and postgresql databases, liquibase
 3. Lombok
 4. Spring REST, Jackson (2.9.8)
 5. Spring Security 5
 6. Java 1.8

## TODOs
* User accounts & authentication: OAUTH2, JWT
* Server disconnection user notification and reconnect
* Prevention of update race condition on Entities.
* Support for Audit fields & logs.
* Use of GraphQL 

## Out of Scope
* Edit Undo via history.
* Multilingual messages
* Use of MQTT 