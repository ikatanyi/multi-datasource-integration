#FROM openjdk:8-jdk-alpine
FROM frolvlad/alpine-oraclejdk8
LABEL maintainer = "kelvin.sasaka@smartapplicationsgroup.com"
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 9820 available to the world outside this container
EXPOSE 8080

ADD target/SMART-AKUH-EDI-SERVICE.jar app.jar

 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


#docker build -t 192.180.4.103:5000/akuh-smartediservice:beta .
#docker push 192.180.4.103:5000/akuh-smartediservice:beta
#https://dzone.com/articles/quick-guide-to-microservices-with-spring-boot-20-e