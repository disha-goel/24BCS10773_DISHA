package com.course.dao;

import com.course.model.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Enrollment-related database operations.
 */
public class EnrollmentDAO {

    /**
     * Inserts a new enrollment record.
     */
    public void enrollStudent(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO Enrollment (studentId, courseId) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    /**
     * Checks if a student is already enrolled in a course.
     * Returns true if duplicate enrollment exists.
     */
    public boolean checkDuplicateEnrollment(int studentId, int courseId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Enrollment WHERE studentId = ? AND courseId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

    /**
     * Retrieves all enrollments with student and course names (joined query).
     */
    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.enrollmentId, e.studentId, e.courseId, " +
                     "s.studentName, c.courseName " +
                     "FROM Enrollment e " +
                     "JOIN Student s ON e.studentId = s.studentId " +
                     "JOIN Course c ON e.courseId = c.courseId";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enrollment en = new Enrollment(
                    rs.getInt("enrollmentId"),
                    rs.getInt("studentId"),
                    rs.getInt("courseId")
                );
                en.setStudentName(rs.getString("studentName"));
                en.setCourseName(rs.getString("courseName"));
                list.add(en);
            }
        }
        return list;
    }
}
