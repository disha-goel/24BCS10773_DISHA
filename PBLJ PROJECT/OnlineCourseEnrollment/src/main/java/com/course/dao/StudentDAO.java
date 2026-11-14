package com.course.dao;

import com.course.model.Student;

import java.sql.*;

/**
 * DAO class for Student-related database operations.
 */
public class StudentDAO {

    /**
     * Finds a student by ID. Returns null if not found.
     */
    public Student getStudentById(int studentId) throws SQLException {
        String sql = "SELECT * FROM Student WHERE studentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("studentId"),
                        rs.getString("studentName")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Inserts a new student if they don't already exist.
     * Returns the existing or newly created student.
     */
    public Student getOrCreateStudent(int studentId, String studentName) throws SQLException {
        Student existing = getStudentById(studentId);
        if (existing != null) {
            return existing;
        }

        String sql = "INSERT INTO Student (studentId, studentName) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, studentName);
            ps.executeUpdate();
        }
        return new Student(studentId, studentName);
    }
}
