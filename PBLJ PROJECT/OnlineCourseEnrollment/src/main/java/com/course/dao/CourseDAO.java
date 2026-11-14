package com.course.dao;

import com.course.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO Course (courseName, maxSeats, enrolledStudents) VALUES (?, ?, 0)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getMaxSeats());
            ps.executeUpdate();
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM Course";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("maxSeats"),
                    rs.getInt("enrolledStudents")
                );
                courseList.add(course);
            }
        }
        return courseList;
    }

    public Course getCourseById(int courseId) throws SQLException {
        String query = "SELECT * FROM Course WHERE courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

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
        String query = "UPDATE Course SET enrolledStudents = enrolledStudents + 1 WHERE courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, courseId);
            ps.executeUpdate();
        }
    }
}
