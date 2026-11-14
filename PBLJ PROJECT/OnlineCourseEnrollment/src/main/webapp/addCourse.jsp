<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Add Course - Registration Portal</title>
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
            max-width: 500px;
            margin: 50px auto;
            background: white;
            padding: 35px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
        }
        h2 {
            color: #1e3a8a;
            margin-top: 0;
            font-size: 22px;
        }
        label {
            display: block;
            margin-top: 18px;
            font-weight: 600;
            color: #334155;
            font-size: 14px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 11px;
            margin-top: 6px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 15px;
            outline: none;
            transition: border-color 0.2s;
        }
        input[type="text"]:focus, input[type="number"]:focus {
            border-color: #2563eb;
        }
        .btn {
            margin-top: 24px;
            padding: 12px 24px;
            background: #16a34a;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            font-weight: 500;
            cursor: pointer;
            width: 100%;
            transition: background 0.2s;
        }
        .btn:hover {
            background: #15803d;
        }
        .error {
            color: #991b1b;
            background: #fee2e2;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 15px;
            font-size: 14px;
            border-left: 4px solid #ef4444;
        }
        .nav-links {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            font-size: 14px;
        }
        .nav-links a {
            color: #2563eb;
            text-decoration: none;
        }
        .nav-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Student Course Registration Portal</h1>
</div>

<div class="container">
    <h2>Add New Course Offering</h2>

    <%
        String error = (String) request.getAttribute("errorMessage");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error"><%= sanitize(error) %></div>
    <%
        }
    %>

    <form action="addCourse" method="post">
        <label for="courseName">Course Code &amp; Title:</label>
        <input type="text" id="courseName" name="courseName" placeholder="e.g. CS205: Computer Networks" maxlength="100" required />

        <label for="maxSeats">Maximum Seat Capacity:</label>
        <input type="number" id="maxSeats" name="maxSeats" placeholder="e.g. 35" min="1" max="500" required />

        <button type="submit" class="btn">Register Course</button>
    </form>

    <div class="nav-links">
        <a href="index.jsp">&larr; Back to Home</a>
        <a href="viewCourses">View Courses &rarr;</a>
    </div>
</div>

</body>
</html>
