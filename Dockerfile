FROM centos
FROM amazoncorretto:17

RUN yum install -y java

VOLUME /tmp
ADD target/payment-transfer-app-2022.1.0.jar payment-transfer.jar
RUN sh -c 'touch /payment-transfer.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payment-transfer.jar"]
