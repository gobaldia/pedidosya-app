# PedidosYa​ ​Fullstack​ ​Technical​ ​Test

This repo contains the solution of the PedidosYa​ ​Fullstack​ ​Technical​ ​Test.

### Description
The project consists in a Spring MVC application.

### How to run
Clone the repo
```
git clone https://github.com/gobaldia/pedidosya-app.git
```

Run the app
```
mvn spring-boot:run
```

### Usage
The web application will run on [localhost:8085/login](http://localhost:8085/login).
After login the user will be redirected to [localhost:8085/home](http://localhost:8085/home).

### Administration endpoints
The admin user will be able to check some administration info in the following endpoints. The admin user must be logged with the following credentials:
> UserName: admin

> Password: admin

#### /auditevents
Lists security audit-related events such as user login/logout.

#### /metrics
Details metrics of our application

#### /health
Summarises the health status of our application