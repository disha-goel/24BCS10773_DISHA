package com.course.service;

import com.course.dao.CourseDAO;
import com.course.dao.EnrollmentDAO;
import com.course.dao.StudentDAO;
import com.course.exception.CourseFullException;
import com.course.exception.CourseNotFoundException;
import com.course.exception.DuplicateEnrollmentException;
import com.course.model.Course;
import com.course.model.Enrollment;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    private boolean isDangerousInput(String text) {
        if (text == null) {
            return false;
        }
        String lower = text.toLowerCase();
        return text.contains("<") || text.contains(">") || lower.contains("script") || lower.contains("javascript:") || lower.contains("onload") || lower.contains("onerror");
    }

    public void addCourse(String courseName, int maxSeats) throws SQLException, IllegalArgumentException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (courseName.trim().length() < 2 || courseName.trim().length() > 100) {
            throw new IllegalArgumentException("Course name must be between 2 and 100 characters.");
        }
        if (isDangerousInput(courseName)) {
            throw new IllegalArgumentException("Invalid input: HTML or script injection tags are not permitted.");
        }
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Maximum seats must be at least 1.");
        }
        if (maxSeats > 500) {
            throw new IllegalArgumentException("Maximum seats cannot exceed 500.");
        }

        Course course = new Course(courseName.trim(), maxSeats);
        courseDAO.addCourse(course);
    }

    public void enrollStudent(int studentId, String studentName, int courseId)
            throws SQLException, CourseNotFoundException, DuplicateEnrollmentException, CourseFullException {

        if (studentId <= 0) {
            throw new IllegalArgumentException("Please provide a valid positive Student ID.");
        }
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (studentName.trim().length() < 2 || studentName.trim().length() > 100) {
            throw new IllegalArgumentException("Student name must be between 2 and 100 characters.");
        }
        if (isDangerousInput(studentName)) {
            throw new IllegalArgumentException("Invalid input: HTML or script injection tags are not permitted.");
        }
        if (courseId <= 0) {
            throw new IllegalArgumentException("Please provide a valid positive Course ID.");
        }

        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course with ID " + courseId + " does not exist.");
        }

        boolean alreadyEnrolled = enrollmentDAO.checkDuplicateEnrollment(studentId, courseId);
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student " + studentName.trim() + " is already enrolled in " + course.getCourseName() + ".");
        }

        if (course.getEnrolledStudents() >= course.getMaxSeats()) {
            throw new CourseFullException("Course '" + course.getCourseName() + "' is currently full (" + course.getMaxSeats() + " seats taken).");
        }

        studentDAO.getOrCreateStudent(studentId, studentName.trim());

        enrollmentDAO.enrollStudent(studentId, courseId);
        courseDAO.incrementEnrolledCount(courseId);
    }

    public List<Course> viewCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    public List<Enrollment> viewEnrollments() throws SQLException {
        return enrollmentDAO.getAllEnrollments();
    }
}
