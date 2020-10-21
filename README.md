# SOEN-343 Project (Team 11)

## Objective
Develop a smart home simulator system.

## Description
As smart home systems become more prevalent in modern households, new problems and challenges will arise. The
smart home simulator will allow users to view the interactions between the household, the devices, and the IoT
system in a graphical interface.  

## Team Members
- Mickael Bou-Samra (ID: 40051348 )
- Kayla Charky (ID: 40099475)
- Michael Lee (ID: 40054375)
- Niels Louis (ID: 40077435)
- Bowen Song (ID: 40092922)


## Technologies

- Java
- Spring/Spring Boot
- JSP 
- Maven
- Junit

## Install Instructions


 
### Set JAVA_HOME

After cloning, you might need to export $JAVA_HOME command as follows:
To show what the path should be: java -XshowSettings:properties -version
java.home = /usr/lib/jvm/java-14-openjdk-amd64
export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
This is what you found in the step before

### JDK
- Make sure you have the jdk 14 downloaded: https://www.oracle.com/ca-en/java/technologies/javase-downloads.html 
 
### Important
Import the file from smart-home-simulator and not SOEN-343-Project.

### After opening the project in IntelliJ
- In IntelliJ, Go to File > Project Structure > Project and import the project SDK that you downloaded from oracle and set the project SDK to 14. 
- In File > Project Structure > Modules, make sure the hava folder in src > main is blue and the java folder in src > test is green
- In File > Settings > Build, Execution, Deployment > Build Tools > Maven > * : Make sure the JDK is imported properly everywhere. 
- Lastly, Make sure the Configuration next to the hammer is pointed to the main application as such. When everything is all set up, you can click test in the maven Side tool or click the refresh button and it should build successfully. 



## TO RUN

You can run this one of two ways: 
- From intelliJ, Press the Green Play button next to the Main of the application. 
- From the Command Line, with: mvn clean spring-boot:run

It will be on localhost:8080/
