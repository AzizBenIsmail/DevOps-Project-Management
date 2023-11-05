FROM openjdk:11
EXPOSE 8085
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-2.5.jar -L "http://localhost:8081/repository/maven-releases/com/esprit/kaddem/2.5/kaddem-2.5.jar"

ENTRYPOINT ["java", "-jar", "kaddem-2.5.jar"]