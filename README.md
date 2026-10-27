# Katalon Technical Test

A test automation project developed as part of a technical assessment, covering REST API validation and Apache Kafka consumer testing using Katalon Studio.

## Overview

This project demonstrates API testing fundamentals and message consumption testing in a local Kafka environment.

### Test Coverage

- REST API testing using Katalon Studio
- HTTP response status and response body validation
- User creation and user retrieval scenarios
- Kafka consumer testing
- Local test environment setup using Docker

## Tech Stack

- **Test Automation:** Katalon Studio
- **Scripting:** Groovy
- **API:** REST API
- **Messaging:** Apache Kafka 4.3.1
- **Containerization:** Docker and Docker Compose

## Project Structure

| Directory / File | Purpose |
|---|---|
| `Test Cases/` | Individual test cases |
| `Test Suites/` | Test suite definitions |
| `Object Repository/` | Reusable test objects |
| `Keywords/` | Custom keywords and reusable logic |
| `Profiles/` | Execution profiles and environment settings |
| `Include/` | Supporting project resources |
| `docker-compose.yml` | Local service configuration |
| `build.gradle` | Gradle build configuration |
| `Katalon-Technical-Test.prj` | Katalon Studio project file |

## REST API Testing

The REST API test cases use the [DummyJSON API](https://dummyjson.com/) as the test target.

### 1. Create User

**Test Case:** `TC_Create_User`

| Attribute | Details |
|---|---|
| HTTP Method | POST |
| Endpoint | `https://dummyjson.com/users/add` |
| Expected Status | HTTP 201 Created |

**Validation objectives:**
- Verify the HTTP response status code.
- Validate the response against the expected behavior.
- Check the returned user data where applicable.

### 2. Get User

**Test Case:** `TC_Get_User`

| Attribute | Details |
|---|---|
| HTTP Method | GET |
| Endpoint | `https://dummyjson.com/users/1` |
| Expected Status | HTTP 200 OK |

**Validation objectives:**
- Verify the HTTP response status code.
- Validate the returned user ID.
- Check that the response body contains the expected user information.

**Note:** DummyJSON is a mock API. A successful user creation response does not necessarily mean the new user is permanently stored.

## Kafka Consumer Testing

This project also covers Kafka consumer testing using Katalon Studio.

**Bootstrap server:** `localhost:9092`

The project includes Kafka-related resources under `Keywords/kafka` and a Docker Compose configuration for the local environment.

### Execution prerequisites

- Docker installed and running.
- Kafka services configured and started according to `docker-compose.yml`.
- Kafka broker accessible at the configured bootstrap address.
- Katalon Studio installed and the project opened successfully.

### Execution steps

1. Start Docker.
2. Start the Kafka environment using the services defined in `docker-compose.yml`.
3. Verify that the Kafka broker is accessible at `localhost:9092`.
4. Open `Katalon-Technical-Test.prj` in Katalon Studio.
5. Locate the relevant Kafka test case or test suite.
6. Configure any required topic, message, and consumer settings.
7. Execute the test and review the execution logs and results.

Refer to the existing project configuration and test scripts for the exact Kafka execution requirements.

## Running the Tests

1. Open the project in Katalon Studio.
2. Review the test cases under `Test Cases/`.
3. Configure the execution profile and any required environment settings.
4. Execute the desired test case or test suite.
5. Review the execution logs and verify the actual results against the expected results.

## Test Results

Record the actual execution results after running the tests.

| Test Case | Expected Result | Actual Result |
|---|---|---|
| `TC_Create_User` | HTTP 201 Created | To be verified |
| `TC_Get_User` | HTTP 200 OK and expected user ID | To be verified |
| Kafka consumer test | Expected message consumption according to the test logic | To be verified |

## Learning Objectives

- Practice API test design and response validation.
- Organize reusable test automation resources in Katalon Studio.
- Explore Kafka consumer testing and local messaging infrastructure.
- Improve test execution, troubleshooting, and documentation practices.

---

**Project:** Katalon Technical Test  
**Focus:** API Testing, Kafka Consumer Testing, and Test Automation
