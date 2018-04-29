# Coinmarket WebApp
Coin Market is an end-to-end web application that has been written on Java-Jersey(Backend) & AngularJS(FrontEnd). this application allows user to check the status of the cryptocurrency that has been marked by the user as favourite.

# Features!

    - User will be able to signup / login
    - User will be able to mark a coin as their favorite in the all coins view.
    - In the dashboard view show a table with the details of all coins he has marked as favorite.

### Prerequisites

Coinmarket requires [Node.js](https://nodejs.org/), [Tomcat 7](http://tomcat.apache.org/) and [MongoDB databse]() to run.

### Geting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

First download the zip and extract it or clone the project. In the extracted folder you will find two folders "client" and "server".

***How to run server?***
- open your eclipse Java EE IDE and import the server project.
- Run server project on Tomcat server.

***How to run client?***
To run the client on local machine execute the following commands in you terminal(Here i'm assuming that, your present working directory is project root directory).
```sh
$ cd client/
$ http-server`
    Starting up http-server, serving ./
    Available on:
      http://127.0.0.1:8081
      http://172.19.20.167:8081
    Hit CTRL-C to stop the server
```
- Now open your browser and use `http://localhost:8081/` to accesss the login page.
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jersey Framwork](https://rometools.github.io/rome/) - Used to create REST webservice

## Contribution
See also the list of [contributors](https://github.com/HarshitSharmaHts/Coin-Market-WebApp/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

**Free Software, Hell Yeah!**