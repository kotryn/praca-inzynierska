# Praca inzynierska
Interfejs użytkownika oparty na koncepcji REST (representational state transfer) dla systemu wyboru odpornego portfela inwestycyjnego

## Getting Started

### Prerequisites

Linux:

1. Maven (1.2 or higher)
`sudo apt-get install mvn` or `sudo apt-get install maven`
2. Nodejs (4.4.5 or higher)
`sudo apt-get install nodejs`
3. Npm (3.9.2 or higher)
`sudo apt-get install npm` <- sprawdzic!!!

### Running project

#### Intellij Idea
1. Configure mvn install (or compile) to execute *before build*.
2. Run

##### If failed, delete: 
`/.m2/repository/com/github/eirslett`

### TO DO:
1. ...

### How to post new user?
In *Advanced REST client*:
1. Method: POST
2. Host: `http://localhost:8080`
3. Path: `/user`
4. Body:``
  {
    "name": "Kate",
    "surname": "Romka",
    "age": 22
  }
  ``
  5. SEND
