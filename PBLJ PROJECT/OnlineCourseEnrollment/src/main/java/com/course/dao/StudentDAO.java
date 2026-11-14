package com.course.dao;

import com.course.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public Student getStudentById(int studentId) throws SQLException {
        String query = "SELECT * FROM Student WHERE studentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

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
        Student existingStudent = getStudentById(studentId);
        if (existingStudent != null) {
            return existingStudent;
        }

        String query = "INSERT INTO Student (studentId, studentName) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ps.setString(2, studentName);
            ps.executeUpdate();
        }
        return new Student(studentId, studentName);
    }
}
