FROM amazoncorretto:17.0.8

RUN addgroup -S colege && adduser -S admin -G colege

USER admin:colege

VOLUME /tmp

ARG JAR_FILE=target/*.jar

ADD target/${JAR_FILE} app.jar

ENV java_opts=""

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
