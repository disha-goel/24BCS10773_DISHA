package com.course.dao;

import com.course.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO Course (courseName, maxSeats, enrolledStudents) VALUES (?, ?, 0)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getMaxSeats());
            ps.executeUpdate();
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                    rs.getInt("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("maxSeats"),
                    rs.getInt("enrolledStudents")
                );
                courses.add(c);
            }
        }
        return courses;
    }

    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT * FROM Course WHERE courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getInt("maxSeats"),
                        rs.getInt("enrolledStudents")
                    );
                }
            }
        }
        return null;
    }

    public void incrementEnrolledCount(int courseId) throws SQLException {
        String sql = "UPDATE Course SET enrolledStudents = enrolledStudents + 1 WHERE courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ps.executeUpdate();
        }
    }
}
