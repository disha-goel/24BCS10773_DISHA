CREATE DATABASE IF NOT EXISTS course_db;
USE course_db;

CREATE TABLE IF NOT EXISTS Course (
    courseId INT AUTO_INCREMENT PRIMARY KEY,
    courseName VARCHAR(100) NOT NULL,
    maxSeats INT NOT NULL,
    enrolledStudents INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Student (
    studentId INT PRIMARY KEY,
    studentName VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Enrollment (
    enrollmentId INT AUTO_INCREMENT PRIMARY KEY,
    studentId INT NOT NULL,
    courseId INT NOT NULL,
    FOREIGN KEY (studentId) REFERENCES Student(studentId),
    FOREIGN KEY (courseId) REFERENCES Course(courseId),
    UNIQUE KEY unique_enrollment (studentId, courseId)
);

INSERT INTO Course (courseName, maxSeats, enrolledStudents) VALUES
('CS201: Object Oriented Programming in Java', 40, 0),
('CS202: Data Structures & Algorithms', 35, 0),
('CS203: Database Management Systems', 30, 0),
('CS204: Web Application Development', 25, 0);
