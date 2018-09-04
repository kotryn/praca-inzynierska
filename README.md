# Praca inzynierska :honeybee:
Interfejs użytkownika oparty na wzorcu REST dla systemu wyboru odpornego portfela inwestycyjnego

## Getting Started

### Prerequisites

Linux:

1. Maven (1.2 or higher)
`sudo apt-get install mvn` or `sudo apt-get install maven`
2. Nodejs (4.4.5 or higher)
`sudo apt-get install nodejs`
3. Npm (3.9.2 or higher)
`sudo apt-get install npm`

### Running project

#### Linux (production)
1. Copy `target/kotryn-0.0.1-SNAPSHOT.jar` and all bash files (`file.sh, file2.sh...`) to one folder
2. Enter this folder and run `java -jar kotryn-0.0.1-SNAPSHOT.jar`
3. Aplication will be run on `http://localhost:8080/`

#### Intellij Idea (development)
1. Change variable *PATH* to project path in file: `/src/main/java/com/kotryn/csv/FileFactory`
2. For the first time: `npm install`
3. Configure mvn install (or compile) to execute *before build*.
4. Run

#### Ubuntu Terminal (development)
1. For the first time: `npm install`
2. Build frontend: `npm start`
3. Run aplication `mvn spring-boot:run`

##### Build only frontend
`mpm start`

##### If failed, delete: 
`/.m2/repository/com/github/eirslett`

#### Run Selenium Tests
1. Start server: `mvn spring-boot:run`
2. Run test: `mvn test`

#### Build (Intellij Idea)
1. Run maven build -> package
