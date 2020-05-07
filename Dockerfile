FROM adoptopenjdk/openjdk8:jre8u252-b09-alpine
WORKDIR /opt/app
COPY build/libs/cloud-engine-hello-world-all.jar application.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "application.jar"]
