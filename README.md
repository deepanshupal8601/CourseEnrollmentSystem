Course Enrollment System 
🚀 Overview
A robust Spring Boot REST API backend to manage courses, instructors, students, and enrollments for an educational institute. This project features multi-layered architecture, clean code, proper database relationships, pagination/sorting support, and centralized error handling.
Note: This repository includes only the backend (no frontend/UI).

📦 Features
Spring Boot 3.5.0, Java 21, Maven, PostgreSQL

Multi-layered architecture (Controller → Service → DAO → Repository)

Entities: Course, Student, Instructor, Enrollment with proper JPA relationships

Full CRUD REST APIs for all entities

Pagination & sorting endpoints for scalable data handling

Custom endpoints for relationships (e.g., get courses by instructor, get enrolled courses per student)

Consistent JSON response structure (with status, message, data)

Global exception handling with custom exceptions

📁 Project Structure (Key Folders)
text
src/main/java/com/jsp/CourseEnrollmentSystem/
├── controller/
├── service/
├── dao/
├── repository/
├── entity/
├── dto/                // for ResponseStructure.java
├── exception/
└── CourseEnrollmentSystemApplication.java
src/main/resources/
├── application.properties
🛠️ Setup Instructions
1. Prerequisites
Java 21+

Maven

PostgreSQL (running instance + a database created)

2. Configure Database in src/main/resources/application.properties
text
spring.datasource.url=jdbc:postgresql://localhost:8080/CourseEnrollmentSystem
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
3. Run Backend Server
bash
mvn spring-boot:run
App will be live at: http://localhost:8080

🗂️ API Endpoints
Courses
GET /course — List all courses

POST /course?instructorId= — Create a course

GET /course/{id} — Get course by ID

PUT /course/{id}?instructor_id= — Update a course

DELETE /course/{id} — Delete a course

GET /course/instructor/{instructorId} — Courses by instructor

GET /course/filtered?... — Paginated, sorted courses

Students
GET /student — List all students

POST /student — Create student

GET /student/{id} — Get student by ID

PUT /student/{id} — Update student

DELETE /student/{id} — Delete student

GET /student/{id}/courses — Courses enrolled by student

GET /student/all?... — Paginated students

Instructors, Enrollments
Similar CRUD and relationship endpoints available; refer to controller code for details.

🧪 API Testing
This backend has been thoroughly tested using Postman.

All endpoints validated through POSTMAN request collections.

To test the API:

Import endpoints into Postman (or use any REST client)

Send requests as per the controller signatures

Use appropriate JSON payloads in POST/PUT requests

Example POST /student

json
{
  "name": "Alice Smith",
  "email": "alice@example.com",
  "dateofBirth": "1995-05-12",
  "percentage": 87.5
}
Example API Response

json
{
  "statusCode": 201,
  "message": "Records saved Successfully",
  "data": { ... }
}
