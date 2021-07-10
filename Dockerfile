# FROM frolvlad/alpine-java

# RUN  apk add --no-cache tzdata ttf-dejavu && ln -sf /usr/share/zoneinfo/Africa/Nairobi /etc/localtime  && echo "Africa/Nairobi" > /etc/timezone

# LABEL maintainer = "kelvin.sasaka@smartapplicationsgroup.com"
# # Add a volume pointing to /tmp
# VOLUME /tmp
# # Make port 9820 available to the world outside this container
# EXPOSE 30080

# ADD target/SMART-AKUH-EDI-SERVICE.jar app.jar

# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

FROM openjdk:8-jdk-alpine
RUN  apk add --no-cache tzdata ttf-dejavu && ln -sf /usr/share/zoneinfo/Africa/Nairobi /etc/localtime  && echo "Africa/Nairobi" > /etc/timezone
LABEL maintainer = "john.wati@smartapplicationsgroup.com"
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 9820 available to the world outside this container
EXPOSE 8303
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# integ-ms-akuh-parklands-edi:latest
