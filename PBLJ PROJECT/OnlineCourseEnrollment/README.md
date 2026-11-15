Online Course Enrollment System

A Java Web Application built with JDBC + Servlets + JSP following the MVC pattern

Features

| Feature                  | URL                  |
|--------------------------|----------------------|
| Home page                | `/`                  |
| Add a course             | `/addCourse`         |
| Enroll a student         | `/enrollStudent`     |
| View courses/enrollments | `/viewCourses`       |


Business Rules

- A student **cannot enroll twice** in the same course → `DuplicateEnrollmentException`
- A course with **no available seats** cannot accept new enrollments → `CourseFullException`
- Enrolling in a **non-existent course ID** → `CourseNotFoundException`

---

Technologies Used

- Java 17
- JDBC (MySQL Connector/J 8.3)
- Jakarta Servlet API 6.0
- JSP
- Apache Tomcat 10.x
- Maven
