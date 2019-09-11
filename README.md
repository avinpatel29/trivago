# Trivago
Integrated automation framework(API & Web based) for the tasks provided.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
What things you need to install the software and how to install them
```
Maven
Java
Eclipse/IntelliJ
```

## Running the tests
1. Git clone https://github.com/avinpatel29/trivago.git
2. Once clone is successful, import the project as “Existing maven project” in IDE (eclipse of IntelliJ)
3. Once the project is imported successfully, you can trigger the test API or UI test as below:
```
For UI:
    mvn clean install -Dcomponent=UI
```
```
For API:
    mvn clean install -Dcomponent=API
```

## Project Structure
As the project is maven based, have followed 'standard' maven project structure as below:

```
src/main/java       - Consists of Page classes & methods
src/main/resources  - Consists of resources utilised by across (such as log4j.properties, setup.properties etc.)   
src/main/test       - Consists of tests to be executed
src/main/resources  - Consists of test data which is required for the tests to run
```

## Built With
* Dependency Management - [Maven](https://maven.apache.org/)  
* Web framework used    - Selenium WebDriver+PageObject Model
* API Framework used    - [RestAssured](http://rest-assured.io/)
* Testing tool          - [TestNG](https://testng.org/doc/)
