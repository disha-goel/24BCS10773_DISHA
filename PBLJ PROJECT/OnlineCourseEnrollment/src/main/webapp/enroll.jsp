<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Enroll Student</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f0f4f8; margin: 0; }
        .header { background: #2c3e50; color: white; padding: 20px 40px; }
        .header h1 { margin: 0; font-size: 22px; }
        .container { max-width: 480px; margin: 50px auto; background: white;
                     padding: 30px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #2c3e50; margin-top: 0; }
        label { display: block; margin-top: 15px; font-weight: bold; color: #333; }
        input[type="text"], input[type="number"] {
            width: 100%; padding: 10px; margin-top: 5px;
            border: 1px solid #ccc; border-radius: 4px;
            box-sizing: border-box; font-size: 15px;
        }
        .btn { margin-top: 20px; padding: 11px 24px; background: #e67e22;
               color: white; border: none; border-radius: 5px;
               font-size: 15px; cursor: pointer; }
        .btn:hover { background: #ca6f1e; }
        .error   { color: #c0392b; background: #fdecea; padding: 10px;
                   border-radius: 4px; margin-bottom: 10px; }
        .success { color: #1e8449; background: #eafaf1; padding: 10px;
                   border-radius: 4px; margin-bottom: 10px; }
        .back { display: inline-block; margin-top: 15px; color: #2980b9; text-decoration: none; }
        .back:hover { text-decoration: underline; }
    </style>
</head>
<body>

<div class="header">
    <h1>Online Course Enrollment System</h1>
</div>

<div class="container">
    <h2>Enroll a Student</h2>

    <%-- Show error or success messages --%>
    <% String error = (String) request.getAttribute("errorMessage");
       if (error != null && !error.isEmpty()) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <% String success = (String) request.getAttribute("successMessage");
       if (success != null && !success.isEmpty()) { %>
        <div class="success"><%= success %></div>
    <% } %>

    <form action="enrollStudent" method="post">
        <label for="studentId">Student ID:</label>
        <input type="number" id="studentId" name="studentId"
               placeholder="e.g. 101" min="1" required />

        <label for="studentName">Student Name:</label>
        <input type="text" id="studentName" name="studentName"
               placeholder="e.g. Alice" required />

        <label for="courseId">Course ID:</label>
        <input type="number" id="courseId" name="courseId"
               placeholder="e.g. 1" min="1" required />

        <button type="submit" class="btn">Enroll Student</button>
    </form>

    <a href="viewCourses" class="back">View All Courses &rarr;</a><br>
    <a href="index.jsp" class="back">&larr; Back to Home</a>
</div>

</body>
</html>
