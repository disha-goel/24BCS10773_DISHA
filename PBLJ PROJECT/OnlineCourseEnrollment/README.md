# Online Course Enrollment System

A Java Web Application built with **JDBC + Servlets + JSP** following the **MVC pattern**.

---

## Folder Structure

```
OnlineCourseEnrollment/
├── pom.xml
├── database/
│   └── schema.sql
└── src/main/
    ├── java/com/course/
    │   ├── model/
    │   │   ├── Course.java
    │   │   ├── Student.java
    │   │   └── Enrollment.java
    │   ├── dao/
    │   │   ├── DBConnection.java
    │   │   ├── CourseDAO.java
    │   │   ├── StudentDAO.java
    │   │   └── EnrollmentDAO.java
    │   ├── service/
    │   │   └── CourseService.java
    │   ├── servlet/
    │   │   ├── AddCourseServlet.java
    │   │   ├── EnrollStudentServlet.java
    │   │   └── ViewCoursesServlet.java
    │   └── exception/
    │       ├── CourseFullException.java
    │       ├── CourseNotFoundException.java
    │       └── DuplicateEnrollmentException.java
    └── webapp/
        ├── index.jsp
        ├── addCourse.jsp
        ├── enroll.jsp
        ├── viewCourses.jsp
        └── WEB-INF/
            └── web.xml
```

---

## How to Run on Apache Tomcat (Step by Step)

### Prerequisites
- Java JDK 17+
- Apache Maven 3.6+
- Apache Tomcat 10.x
- MySQL 8.x

---

### Step 1 — Set Up the Database

1. Open MySQL Workbench or the MySQL command line.
2. Run the SQL script:
   ```sql
   source /path/to/OnlineCourseEnrollment/database/schema.sql
   ```
   This creates the `course_db` database and all three tables.

---

### Step 2 — Configure DB Password

Open `src/main/java/com/course/dao/DBConnection.java` and update:
```java
private static final String PASSWORD = "root"; // ← change to your MySQL password
```

---

### Step 3 — Build the WAR File

Open a terminal in the project root (`OnlineCourseEnrollment/`) and run:
```bash
mvn clean package
```
This generates `target/OnlineCourseEnrollment.war`.

---

### Step 4 — Deploy to Tomcat

1. Copy the WAR file to Tomcat's `webapps/` folder:
   ```
   cp target/OnlineCourseEnrollment.war /path/to/tomcat/webapps/
   ```
2. Start Tomcat:
   ```
   /path/to/tomcat/bin/startup.sh       # Linux/Mac
   /path/to/tomcat/bin/startup.bat      # Windows
   ```
3. Tomcat auto-deploys the WAR.

---

### Step 5 — Open in Browser

```
http://localhost:8080/OnlineCourseEnrollment/
```

---

## Features

| Feature                  | URL                  |
|--------------------------|----------------------|
| Home page                | `/`                  |
| Add a course             | `/addCourse`         |
| Enroll a student         | `/enrollStudent`     |
| View courses/enrollments | `/viewCourses`       |

---

## Business Rules

- A student **cannot enroll twice** in the same course → `DuplicateEnrollmentException`
- A course with **no available seats** cannot accept new enrollments → `CourseFullException`
- Enrolling in a **non-existent course ID** → `CourseNotFoundException`

---

## Technologies Used

- Java 17
- JDBC (MySQL Connector/J 8.3)
- Jakarta Servlet API 6.0
- JSP
- Apache Tomcat 10.x
- Maven
