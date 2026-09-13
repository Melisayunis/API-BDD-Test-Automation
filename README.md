# API BDD Test Automation

[![Weekly API BDD Tests](https://github.com/Melisayunis/API-BDD-Test-Automation/actions/workflows/weekly-tests.yml/badge.svg)](https://github.com/Melisayunis/API-BDD-Test-Automation/actions/workflows/weekly-tests.yml)

API automation project built with **Java, Cucumber, Gherkin and REST Assured**, following a BDD approach.

The project tests the **Restful Booker API** and covers authentication, booking CRUD operations, negative scenarios and data validation.

## Tech Stack

- **Java 21**
- **Maven**
- **Cucumber / Gherkin**
- **REST Assured**
- **JUnit 5**
- **Jackson**
- **GitHub Actions**

## API Under Test

**Restful Booker API**

The project uses the public Restful Booker API:

- API: https://restful-booker.herokuapp.com

No local backend is required to run the tests.

## Project Structure

```text
src/
└── test/
    ├── java/
    │   └── com/melisa/qa/
    │       ├── clients/
    │       │   ├── AuthenticationClient.java
    │       │   └── BookingClient.java
    │       ├── models/
    │       │   └── BookingRequest.java
    │       ├── runners/
    │       │   └── TestRunner.java
    │       └── steps/
    │           ├── AuthenticationSteps.java
    │           └── BookingSteps.java
    │
    └── resources/
        └── features/
            ├── authentication.feature
            └── booking.feature
```

## What Is Being Tested?

### Authentication

- Successful authentication with valid credentials
- Authentication with invalid credentials
- Token generation
- Negative authentication scenarios

### Booking Management

- Create booking
- Retrieve booking
- Update booking
- Delete booking
- Authentication requirements for protected operations
- Validation of booking data

### Data Validation

Different input values are tested for:

- First name
- Last name
- Total price
- Deposit paid
- Additional needs
- Booking dates

The data-driven scenarios use **Cucumber Scenario Outlines** to validate multiple inputs using the same test flow.

Some negative scenarios intentionally verify that invalid data is rejected. If the API accepts data that should be rejected, the test exposes a potential validation or contract issue.

## BDD Approach

The project follows this flow:

```text
Gherkin Feature
      ↓
Cucumber
      ↓
Step Definitions
      ↓
API Client
      ↓
REST Assured
      ↓
Restful Booker API
```

Gherkin scenarios describe the expected behavior, while Java step definitions execute the API requests and assertions.

## Requirements

Before running the project, install:

- **Java JDK 21**
- **Maven 3.9+**
- **Git**

Verify the installations:

```bash
java -version
mvn -version
git --version
```

## Clone the Repository

```bash
git clone <https://github.com/Melisayunis/API-BDD-Test-Automation.git>
```

Navigate to the project:

```bash
cd API-BDD-Test-Automation
```

Maven will automatically download the project dependencies when the tests are executed.

## Run All Tests

```bash
mvn test
```

## Run Tests by Tag

For example, to run only booking tests:

```bash
mvn test "-Dcucumber.filter.tags=@booking"
```

Tags can also be used to execute specific groups of scenarios such as data validation or negative, known defect, data validation, or authentication tests. For example:

```bash
mvn test "-Dcucumber.filter.tags=@booking and @data-validation"
```

Or indicated that dot not have to run an specific tag. Example: 
```bash
mvn test "-Dcucumber.filter.tags=not @known-defect"
```

## Continuous Integration

The test suite is automatically executed using GitHub Actions.

The workflow:

1. Checks out the repository
2. Sets up Java 21
3. Installs Maven dependencies
4. Executes the Cucumber test suite
5. Fails the workflow if any test fails

## Skills Demonstrated

This project demonstrates experience with:

- API Test Automation
- BDD
- Gherkin
- Cucumber
- Java
- REST Assured
- JUnit 5
- REST API validation
- CRUD testing
- Authentication testing
- Positive and negative testing
- Data-driven testing
- JSON request/response validation
- Object-oriented test design
- Maven
- Git
- CI/CD with GitHub Actions

## Author

**Melisa Yunis✨**

🐞 QA Automation Engineer