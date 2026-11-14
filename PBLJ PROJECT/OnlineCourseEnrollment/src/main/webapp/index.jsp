<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Course Enrollment System</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f0f4f8; margin: 0; padding: 0; }
        .header { background: #2c3e50; color: white; padding: 20px 40px; }
        .header h1 { margin: 0; font-size: 26px; }
        .container { max-width: 700px; margin: 60px auto; text-align: center; }
        h2 { color: #2c3e50; }
        p  { color: #555; font-size: 16px; }
        .btn-group { margin-top: 30px; display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; }
        .btn {
            display: inline-block; padding: 14px 28px;
            background: #2980b9; color: white; text-decoration: none;
            border-radius: 6px; font-size: 16px; transition: background 0.2s;
        }
        .btn:hover { background: #1a6fa0; }
        .btn.green { background: #27ae60; }
        .btn.green:hover { background: #1e8449; }
        .btn.orange { background: #e67e22; }
        .btn.orange:hover { background: #ca6f1e; }
    </style>
</head>
<body>

<div class="header">
    <h1>Online Course Enrollment System</h1>
</div>

<div class="container">
    <h2>Welcome!</h2>
    <p>Manage courses and student enrollments easily using the options below.</p>

    <div class="btn-group">
        <a href="addCourse" class="btn green">+ Add New Course</a>
        <a href="enrollStudent" class="btn orange">Enroll a Student</a>
        <a href="viewCourses" class="btn">View All Courses</a>
    </div>
</div>

</body>
</html>
