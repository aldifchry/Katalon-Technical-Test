# Katalon Technical Test

## Overview

This project was created as part of a technical testing assessment.

The project covers:

- RESTful API testing
- Kafka consumer testing

## Tools

- Katalon Studio
- Groovy
- REST API
- Apache Kafka 4.3.1
- Docker

## REST API Testing

Katalon Studio is used to perform REST API testing.

### Create User

Method:

POST

Endpoint:

https://dummyjson.com/users/add

The test validates that the API returns HTTP 201.

Test case:

`TC_Create_User`

### Get User

Method:

GET

Endpoint:

https://dummyjson.com/users/1

The test validates:

- HTTP status code 200
- User ID

Test case:

`TC_Get_User`

## Kafka Testing

Katalon Studio acts as a Kafka consumer.

Kafka:

```text
localhost:9092
