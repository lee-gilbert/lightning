FROM maven:3.5-jdk-8

ADD ./src /code/server/src
ADD ./pom.xml /code/server/pom.xml

WORKDIR /code/server
RUN mvn package -Dmaven.test.skip=true

ENTRYPOINT ["java", "-jar"]
CMD ["target/ltk-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
