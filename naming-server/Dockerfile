FROM openjdk:17
#get jar file from local and assigned to JAR_FILE
ARG JAR_FILE=target/*.jar

#Renamed jar file as namingserver.jar form naming-server-0.0.1.jar
COPY ${JAR_FILE} namingserver.jar

ENTRYPOINT ["java", "-jar", "/namingserver.jar"]

EXPOSE 8761
