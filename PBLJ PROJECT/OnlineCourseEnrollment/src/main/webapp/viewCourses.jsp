<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.course.model.Course" %>
<%@ page import="com.course.model.Enrollment" %>
<%!
    private String sanitize(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course Catalog &amp; Enrollments</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
            color: #1e293b;
        }
        .header {
            background: #1e3a8a;
            color: white;
            padding: 22px 40px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
        }
        .header h1 {
            margin: 0;
            font-size: 22px;
            font-weight: 600;
        }
        .container {
            max-width: 920px;
            margin: 40px auto;
            padding: 0 15px;
        }
        h2 {
            color: #1e3a8a;
            font-size: 20px;
            margin-top: 35px;
            margin-bottom: 15px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
            margin-bottom: 30px;
        }
        th {
            background: #1e3a8a;
            color: white;
            padding: 12px 16px;
            text-align: left;
            font-size: 14px;
            font-weight: 600;
        }
        td {
            padding: 12px 16px;
            border-bottom: 1px solid #e2e8f0;
            font-size: 14px;
            color: #334155;
        }
        tr:last-child td {
            border-bottom: none;
        }
        tr:hover td {
            background: #f8fafc;
        }
        .badge-full {
            background: #dc2626;
            color: white;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 500;
        }
        .badge-open {
            background: #16a34a;
            color: white;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 500;
        }
        .error {
            color: #991b1b;
            background: #fee2e2;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
            border-left: 4px solid #ef4444;
        }
        .nav-bar {
            margin-bottom: 25px;
            display: flex;
            gap: 12px;
        }
        .btn {
            padding: 10px 18px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 500;
            transition: background 0.2s;
        }
        .btn:hover {
            background: #1d4ed8;
        }
        .btn.green {
            background: #16a34a;
        }
        .btn.green:hover {
            background: #15803d;
        }
        .btn.orange {
            background: #d97706;
        }
        .btn.orange:hover {
            background: #b45309;
        }
        .empty-message {
            color: #64748b;
            font-style: italic;
            padding: 16px;
            background: white;
            border-radius: 6px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Student Course Registration Portal</h1>
</div>

<div class="container">

    <div class="nav-bar">
        <a href="index.jsp" class="btn">&larr; Home</a>
        <a href="enrollStudent" class="btn orange">Enroll Student</a>
        <a href="addCourse" class="btn green">+ Add Course</a>
    </div>

    <%
        String error = (String) request.getAttribute("errorMessage");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error"><%= sanitize(error) %></div>
    <%
        }
    %>

    <h2>Available Courses</h2>
    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        if (courses == null || courses.isEmpty()) {
    %>
        <p class="empty-message">No courses currently registered. <a href="addCourse">Add a new course now</a>.</p>
    <%
        } else {
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Course Code &amp; Title</th>
                <th>Capacity</th>
                <th>Enrolled</th>
                <th>Available</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <% for (Course course : courses) { %>
            <tr>
                <td><%= course.getCourseId() %></td>
                <td><%= sanitize(course.getCourseName()) %></td>
                <td><%= course.getMaxSeats() %></td>
                <td><%= course.getEnrolledStudents() %></td>
                <td><%= course.getAvailableSeats() %></td>
                <td>
                    <% if (course.getAvailableSeats() <= 0) { %>
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

    <h2>Active Enrollment Records</h2>
    <%
        List<Enrollment> enrollments = (List<Enrollment>) request.getAttribute("enrollments");
        if (enrollments == null || enrollments.isEmpty()) {
    %>
        <p class="empty-message">No student enrollments recorded yet.</p>
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
                <th>Course Title</th>
            </tr>
        </thead>
        <tbody>
        <% for (Enrollment enrollment : enrollments) { %>
            <tr>
                <td><%= enrollment.getEnrollmentId() %></td>
                <td><%= enrollment.getStudentId() %></td>
                <td><%= sanitize(enrollment.getStudentName()) %></td>
                <td><%= enrollment.getCourseId() %></td>
                <td><%= sanitize(enrollment.getCourseName()) %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

</div>
</body>
</html>
