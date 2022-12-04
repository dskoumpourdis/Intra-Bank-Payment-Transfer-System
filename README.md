# Spring Boot Intra Bank Payment Transfer System Project

This is a sample Java / Maven / Spring Boot (version 3.0.0) application.

## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat installation is necessary. You can run it 
using the ```java -jar``` command.

* Clone this repository
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/intra-bank-payment-transfer-system-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
Once the application runs you should see something like this

```
2022-12-02T22:58:55.652Z  INFO 18440 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-12-02T22:58:55.659Z  INFO 18440 --- [  restartedMain] ntraBankPaymentTransferSystemApplication : Started IntraBankPaymentTransferSystemApplication in 2.832 seconds (process running for 3.106)
```

## About the Service

The service is just a money transfer REST service. It uses an in-memory database (H2) to store the data. 

Here is what this application demonstrates:

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately on the 
  host just run using the ``java -jar`` command
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate.
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* MockMVC test framework with associated libraries

Here are some endpoints you can call:

### Get the statement of an account

```
GET /accounts/{id}/statements

{
    "transactionId": "634D8761-2A39-457D-AA20-D4D690877011",
    "createdAt": "2022-12-04 12:14:14.543",
    "data": {
        "transactions": [
            {
                "accountNum": 111,
                "amount": 100.00,
                "transactionType": "DEBIT",
                "currency": "GBP",
                "transactionDate": "2022-12-04T12:05:58.020+00:00"
            },
            {
                "accountNum": 111,
                "amount": 100.00,
                "transactionType": "CREDIT",
                "currency": "GBP",
                "transactionDate": "2022-12-04T12:05:58.020+00:00"
            }
        ]
    },
    "apiError": null
}

RESPONSE: HTTP 200 (Ok)
```

### Get the balance of an account


```
GET /accounts/{id}/balance

{
    "transactionId": "62598868-1948-405B-A8A9-335DB82C0849",
    "createdAt": "2022-12-04 12:18:20.288",
    "data": {
        "id": 1,
        "accountNum": 111,
        "amount": 1000.00,
        "currency": "GBP"
    },
    "apiError": null
}

RESPONSE: HTTP 200 (Ok)
```

### To view json API documentation

Run the server and browse to ``localhost:8090/v3/api-docs``

### To view the H2 in-memory datbase

The application uses an H2 in-memory database. To view and query the database you can browse to 
http://localhost:8090/h2-console. 
Default username is 'sa' with a blank password. JDBC url is ``jdbc:h2:mem:paymentTransferDB``
