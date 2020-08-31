## Projet Poseidon

Projet 7 : Poseidon : 

Web-Application that aims to generate more transactions for institutional investors who buy and sell fixed income securities.

Logiciel d’entreprise déployé sur le Web qui vise à générer davantage de transactions pour les investisseurs institutionnels qui achètent et vendent des titres à revenu fixe.


A Spring Boot App to expose API Rest. All the URL, expose informations or performs modidication from/in the BDD (MySQL)

# spring-boot
## Technical:

1. Framework: Spring Boot v2.2.6
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create MySQL database with name "poseidon_prod" as configuration in application.properties
5. Due to security Features, a first User with Admin Role must be added in Database.

## Running App
To generate application .jar, run the folowing command :
mvn install

In directory : /Poseiden-skeleton/target/
rename file : spring-boot-skeleton-0.0.1-SNAPSHOT.jar to Poseidon.jar

java -jar Poseidon.jar

## Testing
To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

mvn test

# Reporting
To generate Surefire Report
mvn surefire-report:report

To generate Reports (ex Jacoco)
mvn site

