# Trivia App Assessment

This is a simple Java Spring Boot web application that wraps the [Open Trivia Database API](https://opentdb.com/).
It provides a backend API and a very basic frontend so that users can answer trivia questions without being able to see the correct answers directly in the JSON.

---

## Project Overview

The task was to create:

* A backend in Java using Spring Boot that talks to the Open Trivia Database API.

  * Exposes two endpoints:

    * `GET /questions` → fetches a trivia question and possible answers
    * `POST /checkanswers` → checks if the submitted answer is correct
* A frontend (basic HTML + JavaScript) so that users can:

  * Request new questions
  * Select and submit answers
  * See whether their answer was correct

No database was required for this assessment. The project is kept intentionally simple for clarity.

---

### Demo Video

A short demo video showcasing the application will be placed here.
(Link or embedded video to be added later)

---


## How to Run

### 1. Prerequisites

* Java 17 or newer installed
* Maven installed

You can check versions with:

```bash
java -version
mvn -v
```

### 2. Clone the Repository

```bash
git clone https://github.com/your-username/trivia_app_assessment.git
cd trivia_app_assessment
```

### 3. Build and Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or build a JAR and run it:

```bash
mvn clean package
java -jar target/trivia_app_assessment-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application

* Backend endpoints:

  * `http://localhost:8080/questions`
  * `http://localhost:8080/checkanswers`
* Frontend UI:

  * Open `http://localhost:8080` in your browser

---

## Assessment Context

This project was created as part of a coding assessment.
The goal was to demonstrate:

* Setting up a Spring Boot backend
* Consuming and wrapping an external API
* Serving a simple frontend

---
