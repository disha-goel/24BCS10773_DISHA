package com.course.dao;

import com.course.model.Student;

import java.sql.*;

public class StudentDAO {

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
