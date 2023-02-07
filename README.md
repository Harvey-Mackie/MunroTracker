# MunroTracker
Track which Munros you have completed and compare against your friends.
## Requirements 
- Set up a Postgres database called Munros - if you have not used Postgres before then [here]("https://www.youtube.com/watch?v=BLH3s5eTL4Y&t=664s&ab_channel=Amigoscode") is a good video to get set-up.
- Download [Java JDK]("https://www.oracle.com/java/technologies/javase/19-0-1-relnotes.html") - currently using version 19.0.1.
- Download [Maven 3]("https://maven.apache.org/")

## Run Locally
- Set-up database called Munros as mentioned in **Requirements**.
- Execute the `main` method in the `com.munro.api.ApiApplication` class.

## Commits
- The [Conventional Commits]("https://www.conventionalcommits.org/en/v1.0.0/") specification is used for all commits. 
- We want to keep a liner commit graph to ensure readability.

## Munro Data
- Currently, it is extracted from a CSV held in `com.munro.api.data.munros.csv`.
- The database is populated with the Munros when the application starts. 
- To add all the Munros, update the following properties in `application.properties`:
  - `app.munroCountCap = 300`
  - `app.refreshMunroDatabase = true`
  - `spring.jpa.hibernate.ddl-auto=create-drop`
  