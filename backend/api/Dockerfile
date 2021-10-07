FROM gradle:7.2-jdk11-hotspot as build

WORKDIR /home/gradle/src

COPY build.gradle .

COPY settings.gradle .

COPY src src

RUN gradle build --no-daemon

FROM adoptopenjdk:11-jre-hotspot-focal

COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar

COPY docker-entrypoint.sh /

ENTRYPOINT ["/docker-entrypoint.sh"]
