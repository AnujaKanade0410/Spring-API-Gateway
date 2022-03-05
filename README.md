# Spring-API-Gateway
Demo for API gateway using Spring cloud gateway
API gateway:
application name: api-gw
port: 8080

Microservice 1: savings-a
port: 8081

Microservice 2: saving-b
port: 8082

Url to get the balance from savings account a:
http://localhost:8080/savings/a/balance   GET

Url to get the balance from savings account b:
http://localhost:8080/savings/b/balance   GET

Url to update the balance for savings account a:
http://localhost:8080/savings/a/balance   POST
with Json request:
{"amount":"2000.0"}

Note: Provide negative value for amount to decrese the balance. Eg:
{"amount":"-2000.0"}
