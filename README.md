# 🛡️ AI Safety Incident Log API - HumanChain Backend Intern Assignment

---

## 📚 Project Overview
This project is a simple **Spring Boot** REST API to log and manage hypothetical AI safety incidents.  
It fulfills the requirements of the **HumanChain Backend Intern Take-Home Assignment**.

---

## ⚙️ Tech Stack
- **Language**: Java 17
- **Framework**: Spring Boot 3
- **Database**: MySQL

---

## 🚀 How to Run the Project Locally

1. **Set Up MySQL Database**
    - Create a database named `ai_safety` (or whatever name you used).
    - Example:
      ```sql
      CREATE DATABASE ai_safety;
      ```

2. **Configure `application.properties`**
   ```properties
   # Database Connection 
    spring.application.name=ai-safety
    spring.jpa.show-sql=true
    spring.datasource.username=root
    spring.datasource.password= 'your database password'
    spring.datasource.url=jdbc:mysql://localhost:3306/ai_safety
    spring.sql.init.mode=always
   ```

3. **Clone the Repository**
   ```bash
   git clone https://github.com/vk28122812/ai-safety-incident-log.git
   cd ai-safety-incident-log
   ```

4. **Build and Run**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**
    - API Base URL: `http://localhost:8080`
---

## 🗄️ Database Schema

| Field        | Type      | Details                                                     |
|--------------|-----------|-------------------------------------------------------------|
| id           | Integer   | Auto-generated Primary Key                                  |
| title        | String    | Required, must not be blank                                 |
| description  | String    | Required, must not be blank                                 |
| severity     | String    | Must be \"LOW\", \"MEDIUM\", or \"HIGH\" (case-insensitive) |
| reported_at  | Date      | Auto-generated :  default current timestamp                 |

✅ Validation handled using **Jakarta Bean Validation** annotations.  
✅ Dummy records prefilled using `data.sql` file.

---

## 📖 API Endpoints

### 1. Get All Incidents
- **Method:** `GET`
- **Endpoint:** `/incidents`
- **Response Example:**
```json
[
  [
    {
      "id": 1,
      "title": "Network Outage",
      "description": "A major network outage affecting multiple regions.",
      "severity": "HIGH",
      "reported_at": "2023-10-01"
    },
    {
      "id": 2,
      "title": "Server Maintenance",
      "description": "Scheduled maintenance for server upgrades.",
      "severity": "MEDIUM",
      "reported_at": "2023-09-15"
    },
    {
      "id": 3,
      "title": "Login Issue",
      "description": "Users are unable to log in to the system.",
      "severity": "HIGH",
      "reported_at": "2023-10-05"
    }
]
```

---

### 2. Create New Incident
- **Method:** `POST`
- **Endpoint:** `/incidents`
- **Request Body Example:**
```json
{
  "title": "Unauthorized Access",
  "description": "The AI accessed restricted data.",
  "severity": "High"
}
```
- **Validation Rules:**
    - Title, Description, Severity, and Reported Date are mandatory.
    - Severity must be **LOW**, **MEDIUM**, or **HIGH** (case-insensitive).
    - `reported_at` must be a past date.

- **Response Example (201 Created):**
```json
{
  "id": 6,
  "title": "Unauthorized Access",
  "description": "The AI accessed restricted data.",
  "severity": "High",
  "reported_at": "2025-04-27"
}
```

- **Error:**  
  Returns `400 Bad Request` for invalid input.
```json
{
    "timestamp": "2025-04-27 21:23:11",
    "statusCode": 400,
    "path": "/incidents",
    "message": "Validation failed for argument [0] in public com.assignment.ai_safety.Incident com.assignment.ai_safety.IncidentController.addIncident(com.assignment.ai_safety.Incident): [Field error in object 'incident' on field 'title': rejected value [null]; codes [NotNull.incident.title,NotNull.title,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [incident.title,title]; arguments []; default message [title]]; default message [Title cannot be null]] "
}
```
---

### 3. Get Incident by ID
- **Method:** `GET`
- **Endpoint:** `/incidents/{id}`
- **Response Example:**
```json
{
  "id": 1,
  "title": "Network Outage",
  "description": "A major network outage affecting multiple regions.",
  "severity": "HIGH",
  "reported_at": "2023-10-01"
}
```
- **Error:** `404 Not Found` if ID does not exist.
```json
{
  "timestamp": "2025-04-27 21:26:58",
  "statusCode": 404,
  "path": "/incidents/7",
  "message": "Incident with id: 7 not found"
}
```
---

### 4. Delete Incident by ID
- **Method:** `DELETE`
- **Endpoint:** `/incidents/{id}`
- **Response Example (on success):**
```json
{
  "status": "success",
  "message": "Incident with id: 6 deleted successfully"
}
```
- **Error:** `404 Not Found` if ID does not exist.
```json
{
    "timestamp": "2025-04-27 21:27:59",
    "statusCode": 404,
    "path": "/incidents/60",
    "message": "Incident with id: 60 not found"
}
```
---

## ⚡ Error Handling
All exceptions are caught globally by a `@ControllerAdvice`.
- `404 Not Found` for missing incidents.
- `400 Bad Request` for validation failures.

Example error response:
```json
{
  "timestamp": "2025-04-27 21:28:34",
  "statusCode": 404,
  "path": "/incidents/70",
  "message": "Incident with id: 70 not found"
}
```

---

## ✨ Design Decisions
- **Service Layer** handles business logic.
- **DTO Validation** ensures clean input.
- **Prefilling data** through `data.sql` makes testing easy.
- **MySQL** chosen for realistic production-like database behavior.

---

