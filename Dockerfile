FROM gradle:7.0.2-jdk11 AS builder

COPY . /home/exoplanet
WORKDIR /home/exoplanet

# Build the project using Gradle
RUN gradle build --info

FROM adoptopenjdk:11-jre-hotspot

# Copy the built JAR file from the builder stage to the production stage
COPY --from=builder /home/exoplanet/build/libs/exoplanet-*-all.jar exoplanet.jar

EXPOSE 8080

# command to run when the container starts
CMD ["java", "-jar", "exoplanet.jar"]



