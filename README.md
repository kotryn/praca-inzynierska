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

#### Intellij Idea (development)
1. Change variable *PATH* to project path in file: `/src/main/java/com/kotryn/csv/FileFactory`
2. For the first time: `npm install`
3. Configure mvn install (or compile) to execute *before build*.
4. Run
5. Run localtunnel: `lt --subdomain kotryn --port 8080`
6. Application will be run on `http://kotryn.localtunnel.me/`

#### Ubuntu Terminal (development)
1. For the first time: `npm install`
2. Build frontend: `npm start`
3. Run application `mvn spring-boot:run`
4. Run localtunnel: `lt --subdomain kotryn --port 8080`
5. Application will be run on `http://kotryn.localtunnel.me/`

##### Build only frontend
`mpm start`