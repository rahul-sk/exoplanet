## Exoplanet API Service
Exoplanet API Sercive exposes APIs to fetch exoplanet information. 

### Supported APIs
- /api/v1/getorphancount       : To fetch the number of orphan planets (no star).
- /api/v1/gethotteststarplanet : The name (planet identifier) of the planet orbiting the hottest star.
- /api/v1/getplanettimeline    : A timeline of the number of planets discovered per year grouped by size.

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

## Screenshots
- /api/v1/getorphancount
  <img width="851" alt="Screenshot 2023-06-15 at 12 40 12 PM" src="https://github.com/kponnima/exercise-exoplanet-catalogue/assets/37139616/516dd7b5-b0cb-491a-b053-83af7f46cffe">

- /api/v1/gethotteststarplanet
  <img width="851" alt="Screenshot 2023-06-15 at 12 44 05 PM" src="https://github.com/kponnima/exercise-exoplanet-catalogue/assets/37139616/0c51321b-6078-4ce9-823e-04705da6194b">

- /api/v1/getplanettimeline
  <img width="851" alt="Screenshot 2023-06-15 at 12 46 29 PM" src="https://github.com/kponnima/exercise-exoplanet-catalogue/assets/37139616/18690c10-0bc5-465f-af92-c61cf697e093">
  
