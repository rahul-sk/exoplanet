FROM gradle:7.0.2-jdk11 AS builder

COPY . /home/exoplanet
WORKDIR /home/exoplanet

RUN gradle build --info

FROM adoptopenjdk:11-jre-hotspot

COPY --from=builder /home/exoplanet/build/libs/exoplanet-*-all.jar exoplanet.jar

EXPOSE 8080

CMD ["java", "-jar", "exoplanet.jar"]

#FROM builder AS test-runner
#WORKDIR /home/exoplanet
#CMD gradle test --no-daemon



