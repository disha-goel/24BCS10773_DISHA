<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.course.model.Course" %>
<%@ page import="com.course.model.Enrollment" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Courses</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f0f4f8; margin: 0; }
        .header { background: #2c3e50; color: white; padding: 20px 40px; }
        .header h1 { margin: 0; font-size: 22px; }
        .container { max-width: 860px; margin: 40px auto; }
        h2 { color: #2c3e50; }
        table { width: 100%; border-collapse: collapse; background: white;
                border-radius: 8px; overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 40px; }
        th { background: #2c3e50; color: white; padding: 12px 15px; text-align: left; }
        td { padding: 11px 15px; border-bottom: 1px solid #eee; }
        tr:last-child td { border-bottom: none; }
        tr:hover td { background: #f7f9fc; }
        .badge-full { background: #e74c3c; color: white; padding: 3px 8px;
                      border-radius: 12px; font-size: 12px; }
        .badge-open { background: #27ae60; color: white; padding: 3px 8px;
                      border-radius: 12px; font-size: 12px; }
        .error { color: #c0392b; background: #fdecea; padding: 10px;
                 border-radius: 4px; margin-bottom: 15px; }
        .nav { margin-bottom: 20px; display: flex; gap: 15px; }
        .btn { padding: 10px 20px; background: #2980b9; color: white;
               text-decoration: none; border-radius: 5px; font-size: 14px; }
        .btn:hover { background: #1a6fa0; }
        .btn.green { background: #27ae60; }
        .btn.green:hover { background: #1e8449; }
        .btn.orange { background: #e67e22; }
        .btn.orange:hover { background: #ca6f1e; }
        .empty { color: #888; font-style: italic; padding: 15px; }
    </style>
</head>
<body>

<div class="header">
    <h1>Online Course Enrollment System</h1>
</div>

<div class="container">

    <div class="nav">
        <a href="index.jsp" class="btn">&larr; Home</a>
        <a href="addCourse" class="btn green">+ Add Course</a>
        <a href="enrollStudent" class="btn orange">Enroll Student</a>
    </div>

    <%-- Error message --%>
    <% String error = (String) request.getAttribute("errorMessage");
       if (error != null && !error.isEmpty()) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <%-- ===== COURSES TABLE ===== --%>
    <h2>All Courses</h2>
    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        if (courses == null || courses.isEmpty()) {
    %>
        <p class="empty">No courses found. <a href="addCourse">Add one now.</a></p>
    <%
        } else {
    %>
    <table>
        <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Max Seats</th>
                <th>Enrolled</th>
                <th>Available</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <% for (Course c : courses) { %>
            <tr>
                <td><%= c.getCourseId() %></td>
                <td><%= c.getCourseName() %></td>
                <td><%= c.getMaxSeats() %></td>
                <td><%= c.getEnrolledStudents() %></td>
                <td><%= c.getAvailableSeats() %></td>
                <td>
                    <% if (c.getAvailableSeats() <= 0) { %>
                        <span class="badge-full">Full</span>
                    <% } else { %>
                        <span class="badge-open">Open</span>
                    <% } %>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <%-- ===== ENROLLMENTS TABLE ===== --%>
    <h2>Enrollment Records</h2>
    <%
        List<Enrollment> enrollments = (List<Enrollment>) request.getAttribute("enrollments");
        if (enrollments == null || enrollments.isEmpty()) {
    %>
        <p class="empty">No enrollments yet.</p>
    <%
        } else {
    %>
    <table>
        <thead>
            <tr>
                <th>Enrollment ID</th>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Course ID</th>
                <th>Course Name</th>
            </tr>
        </thead>
        <tbody>
        <% for (Enrollment en : enrollments) { %>
            <tr>
                <td><%= en.getEnrollmentId() %></td>
                <td><%= en.getStudentId() %></td>
                <td><%= en.getStudentName() %></td>
                <td><%= en.getCourseId() %></td>
                <td><%= en.getCourseName() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

</div>
</body>
</html>
