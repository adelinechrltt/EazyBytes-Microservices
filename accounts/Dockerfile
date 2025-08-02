FROM openjdk:24-jdk-slim

LABEL maintainer="github.com/adelinechrltt"

# COPY
# For copying a file from local project directory to Docker image during build process
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# ENTRYPOINT
# For defining the list of instructions to be excecuted whenever the Docker container is run.
# E.g.
# ENTRYPOINT ["java", "-jar", accounts-0.0.1-SNAPSHOT.jar] --> run the "java" and "-jar" commands with the accounts-0.0.1-SNAPSHOT.jar as the target .jar
ENTRYPOINT ["java", "-jar", accounts-0.0.1-SNAPSHOT.jar]