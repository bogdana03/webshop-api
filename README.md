# Webshop backend application

## Setup
The webshop-api application requires JDK 8 OR 11 to run in the cloud. 
That can be downloaded here:
* https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html

Unzip that file in a location like C:\jdk\jdk11.0.13_8

You will then have to configure the java path in the file `gradle.properties`

## Linux/Mac OS specifics
The project is configured to run gradle with the gradle wrapper.
This might cause issues on linux/mac operating systems.
If such a problem does occur, try running `sudo chmod +x gradlew`.
It is also a possibility with problems due to different line endings on linux/mac and windows.
That should be fixed with `sed -i.bak 's/\r$//' gradlew`

## Swagger UI
Go to [Swagger UI](http://localhost:5000) of your running application

## Useful commands
 * `gradlew webshop-api:bootRun` Will start your spring boot application locally, making it accessible from localhost:5000
 * `gradlew webshop-api:bootJar` Will package a deployable JAR from your spring boot application
 * To upload your executable JAR file to S3:
```
aws s3api put-object --bucket GROUPNAME-storage --key webshop-api-1.0.0-SNAPSHOT.jar --body webshop-api\build\libs\webshop-api-1.0.0-SNAPSHOT.jar
```