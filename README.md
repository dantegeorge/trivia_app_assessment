# Trivia App Assessment

This is a simple **Java Spring Boot backend** application that wraps the [Open Trivia Database API](https://opentdb.com/).  
It provides a backend API so that users can answer trivia questions without being able to see the correct answers directly in the JSON. The frontend is built in **Next.js** with TailwindCSS for styling.

---

## Project Overview

The task was to create:

* A backend in **Java Spring Boot** that communicates with the Open Trivia Database API:
    * Exposes two endpoints:
        * `GET /api/questions` → fetches a trivia question with possible answers
        * `POST /api/check-answers` → checks if the submitted answer is correct

* A frontend in **Next.js** so that users can:
    * Request new trivia questions
    * Select and submit answers
    * See whether their answer was correct

No database is required for this assessment. The project is intentionally kept simple for clarity.

---

### Demo Video

[![Watch the demo](https://img.youtube.com/vi/Xs9bMp7YZNk/0.jpg)](https://youtu.be/Xs9bMp7YZNk)

---

## How to Run

### 1. Prerequisites

* **Java 17** or newer installed
* **Maven** installed
* **Node.js 18+** installed (for Next.js frontend)
* **npm** or **yarn** installed

Check versions:

```bash
java -version
mvn -v
node -v
npm -v
```

### 2. Clone the Repository

git clone https://github.com/dantegeorge/trivia_app_assessment.git
cd trivia_app_assessment

### 3. Run the Backend

Using Maven:

```bash
mvn spring-boot:run
```
Or build a JAR and run:

```bash
mvn clean package
java -jar target/trivia_app_assessment-0.0.1-SNAPSHOT.jar
```
Backend will run on http://localhost:8080.

Available endpoints:

    GET http://localhost:8080/api/questions

    POST http://localhost:8080/api/check-answers (JSON payload: {"question": "...", "chosenAnswer": "..."})

### 4. Run the Frontend (Next.js)

```bash
cd trivia_frontend
npm install       # or yarn
npm run dev       # or yarn dev
```

Frontend will run on http://localhost:3000 and automatically fetch questions from the backend.

### 5. Access the Application

Open your browser to http://localhost:3000

Click Get Question, select an answer, and see feedback in the UI.

---