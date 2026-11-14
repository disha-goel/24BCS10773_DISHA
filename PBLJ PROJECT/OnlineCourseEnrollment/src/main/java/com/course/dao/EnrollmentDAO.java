package com.course.dao;

import com.course.model.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public void enrollStudent(int studentId, int courseId) throws SQLException {
        String query = "INSERT INTO Enrollment (studentId, courseId) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    public boolean checkDuplicateEnrollment(int studentId, int courseId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Enrollment WHERE studentId = ? AND courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> enrollmentList = new ArrayList<>();
        String query = "SELECT e.enrollmentId, e.studentId, e.courseId, s.studentName, c.courseName " +
                "FROM Enrollment e " +
                "JOIN Student s ON e.studentId = s.studentId " +
                "JOIN Course c ON e.courseId = c.courseId " +
                "ORDER BY e.enrollmentId DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                    rs.getInt("enrollmentId"),
                    rs.getInt("studentId"),
                    rs.getInt("courseId")
                );
                enrollment.setStudentName(rs.getString("studentName"));
                enrollment.setCourseName(rs.getString("courseName"));
                enrollmentList.add(enrollment);
            }
        }
        return enrollmentList;
    }
}
