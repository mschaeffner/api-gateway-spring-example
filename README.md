# api-gateway-spring-example

This is a simple Spring Boot application that acts as an API gateway. It has only one endpoint.


## GET /users/{userId}

Calls the following endpoints at [http://jsonplaceholder.typicode.com] API and combines their results into a single JSON output:
* http://jsonplaceholder.typicode.com/users/{userId}
* http://jsonplaceholder.typicode.com/posts?userId={userId}


## How to run

* mvn spring-boot:run
* go to [http://localhost:8080/users/1] to see result for user with id 1


## Technologies used

* Spring Boot and Spring Web
* Retrofit
* Maven
