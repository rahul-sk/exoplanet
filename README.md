## Exoplanet API Sercive
Exoplanet API Sercive exposes APIs to fetch exoplanet information. 

### Pre-Requisites
- Git
- OpenJDK 16 Hotspot
- Docker
- Gradle 7.2.1

## Cloning the project
Github Url: https://github.com/rahul-sk/exoplanet.git

```
 git clone https://github.com/rahul-sk/exoplanet.git
```
---
## Build and Run the application

```shell
./gradlew build

MICRONAUT_ENVIRONMENTS=local ./gradlew run
  ```

---

### Run the application in a docker container
From the root directory of the project
Run the following :

```shell
docker build -t exoplanet:latest .
docker run -p 8088:8080 exoplanet
```
## Executing Unit Tests

```
 ./gradlew test
```
