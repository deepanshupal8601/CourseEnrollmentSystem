# 📘 Course Enrollment System

## 🚀 Overview  
     A robust Spring Boot REST API backend for managing **courses**, **instructors**, **students**, and **enrollments** in an educational institute. This project uses a         clean, multi-layered architecture with strong JPA relationships, consistent response structure, pagination, and global exception handling.  

---

## 📦 Tech Stack
     - Java 21  
     - Spring Boot 3.5.0  
     - PostgreSQL  
     - Postman (for API testing)  
---

## ✅ Features


     - Full CRUD REST APIs for all entities  
     - Multi-layered architecture: `Controller → Service → DAO → Repository`  
     - JPA Entities with relationships:
     - `Course`
     - `Instructor`
     - `Student`
     - `Enrollment`
     - Pagination & sorting support  
     - Relationship endpoints (e.g., get courses by instructor, get student  enrollments)  
     - Unified JSON response format using `ResponseStructure`  
     - Global exception handling with custom exception classes  

---

## 📁 Project Structure
     src/main/java/com/jsp/CourseEnrollmentSystem/
     ├── controller/  
     ├── service/
     ├── dao/
     ├── repository/
     ├── entity/
     ├── dto/                 // For ResponseStructure.java
     ├── exception/
     └── CourseEnrollmentSystemApplication.java

      src/main/resources/
      └── application.properties


🛠️ Setup Instructions
1. Prerequisites

        * Java 21+
        * Maven
        *PostgreSQL (running instance with a database)

2. Configure application.properties

       spring.datasource.url=jdbc:postgresql://localhost:5432/CourseEnrollmentSystem
       spring.datasource.username=YOUR_USERNAME
       spring.datasource.password=YOUR_PASSWORD
       spring.jpa.hibernate.ddl-auto=update
       spring.jpa.show-sql=true
       spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

4. Run the Application

          mvn spring-boot:run


📂 API Endpoints
📘 Courses

       GET /course — List all courses

       POST /course?instructorId= — Create a course

       GET /course/{id} — Get course by ID

       PUT /course/{id}?instructor_id= — Update a course

       DELETE /course/{id} — Delete a course

       GET /course/instructor/{instructorId} — Courses by instructor

        GET /course/filtered?... — Paginated and sorted courses

👨‍🎓 Students
 
      GET /student — List all students

      POST /student — Create student

     GET /student/{id} — Get student by ID

     PUT /student/{id} — Update student

     DELETE /student/{id} — Delete student

    GET /student/{id}/courses — Enrolled courses of a student

     GET /student/all?... — Paginated students

🧪 API Testing

     This backend is thoroughly tested using Postman.

     All endpoints are validated using Postman collection

    Import and test using appropriate payloads


     Example POST /student Payload:
     json
     Copy code
     {
      "name": "Alice Smith",
      "email": "alice@example.com",
     "dateofBirth": "1995-05-12",
     "percentage": 87.5
     }
    Example API Response:
    json
    Copy code
      {
       "statusCode": 201,
        "message": "Record saved successfully",
         "data": {
    // created object
     }
    }


