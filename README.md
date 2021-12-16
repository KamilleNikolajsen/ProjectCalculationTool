# ProjectCalculationTool

ProjectCalculationTool is a tool that allows users to create projects, subprojects and tasks where task contains a time in hours.
The hours will be calculated into workdays consitent of a maximum of 8 hour days, and give an estimate for how long the project is going to take. 
The main page will now display the number of days each project will take including the amount of subprojects it contains.

## Prerequisites
It is recommended to install IntelliJ and MySQL workbench.

Otherwise before you begin be sure to have:
- Compiler that runs Java and supports:
    - Thymeleaf
    - JDBC
    - MySQL
    - Spring
    - Maven
- Database Manager that supports MySQL

## Using ProjectCalculationTool 
To use this project:
- Fork the repository 
- Clone locally
- Create database using the sql scripts from the program in your local database maneger. starting with the start.sql and thereafter the testdata.sql
- Set your envierment variables: user(username for local database), password(password for local database), url(jdbc:mysql://hostname:3306/projectcalculationtool?      reconnect=true&autoReconnect=true) change hostname to local hostname
- Go into the ui testclasses:
    - Select the webdriver that suits you, Mac or Windows. 
    - Select the url for where you want to test, Local or on Heroku.
- Run program 
- Open the webbrowser to localhost:8080
    - If you want to host one your own Heroku account:
    - Go to Heroku and connect to your cloned repository
    - Choose the branche you want to deploy
    - Deploy
- Login to the program using the login username: test@yes.com and password: 123
- Herafter you can create projects.
- After creating a project you can click into the project to add subprojects and tasks

## Contributing to ProjectCalculationTool
To contribut to ProjectCalculationTool do the following:
- Fork the repository
- Create a branch: git checkout -b banchName
- Make your contribution and commit them: git commit -m"message"
- Push to origin branch: git push origin ProjectCalculationTool/location
- Create pull request

# Contributors
The Contributors of this project:

- [@MichalaNybroe](https://github.com/MichalaNybroe)
- [@KamilleNikolajsen](https://github.com/KamilleNikolajsen)
- [@Rasmus-Kibsehde](https://github.com/Rasmus-Kibshede)
- [@TheeCapain-AugustHauerslev](https://github.com/TheeCapain)
