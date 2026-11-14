<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Course Registration Portal</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
            padding: 0;
            color: #1e293b;
        }
        .header {
            background: #1e3a8a;
            color: white;
            padding: 24px 40px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
            font-weight: 600;
        }
        .container {
            max-width: 760px;
            margin: 60px auto;
            text-align: center;
            background: white;
            padding: 45px 35px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }
        h2 {
            color: #1e3a8a;
            margin-top: 0;
            font-size: 26px;
        }
        p {
            color: #64748b;
            font-size: 16px;
            line-height: 1.6;
            margin-bottom: 35px;
        }
        .btn-group {
            display: flex;
            justify-content: center;
            gap: 18px;
            flex-wrap: wrap;
        }
        .btn {
            display: inline-block;
            padding: 13px 26px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 15px;
            font-weight: 500;
            transition: all 0.2s ease;
        }
        .btn:hover {
            background: #1d4ed8;
            transform: translateY(-1px);
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
    </style>
</head>
<body>

<div class="header">
    <h1>Student Course Registration Portal</h1>
</div>

<div class="container">
    <h2>Welcome to Course Registration</h2>
    <p>A web-based course management system for browsing academic courses, adding new course offerings, and managing student enrollments.</p>

    <div class="btn-group">
        <a href="viewCourses" class="btn">View All Courses</a>
        <a href="enrollStudent" class="btn orange">Enroll a Student</a>
        <a href="addCourse" class="btn green">+ Add New Course</a>
    </div>
</div>

</body>
</html>
