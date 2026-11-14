<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
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
        .btn { margin-top: 20px; padding: 11px 24px; background: #27ae60;
               color: white; border: none; border-radius: 5px;
               font-size: 15px; cursor: pointer; }
        .btn:hover { background: #1e8449; }
        .error   { color: #c0392b; background: #fdecea; padding: 10px;
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
    <h2>Add New Course</h2>

    <%-- Show error message if any --%>
    <% String error = (String) request.getAttribute("errorMessage");
       if (error != null && !error.isEmpty()) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="addCourse" method="post">
        <label for="courseName">Course Name:</label>
        <input type="text" id="courseName" name="courseName"
               placeholder="e.g. Java Programming" required />

        <label for="maxSeats">Maximum Seats:</label>
        <input type="number" id="maxSeats" name="maxSeats"
               placeholder="e.g. 30" min="1" required />

        <button type="submit" class="btn">Add Course</button>
    </form>

    <a href="index.jsp" class="back">&larr; Back to Home</a>
</div>

</body>
</html>
