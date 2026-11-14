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

    public void addCourse(String courseName, int maxSeats) throws SQLException, IllegalArgumentException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Max seats must be greater than 0.");
        }

        Course course = new Course(courseName.trim(), maxSeats);
        courseDAO.addCourse(course);
    }

    public void enrollStudent(int studentId, String studentName, int courseId)
            throws SQLException, CourseNotFoundException, DuplicateEnrollmentException, CourseFullException {

        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be greater than 0.");
        }
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be greater than 0.");
        }

        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course with ID " + courseId + " was not found.");
        }

        boolean isDuplicate = enrollmentDAO.checkDuplicateEnrollment(studentId, courseId);
        if (isDuplicate) {
            throw new DuplicateEnrollmentException("Student " + studentName.trim() + " is already enrolled in " + course.getCourseName() + ".");
        }

        if (course.getEnrolledStudents() >= course.getMaxSeats()) {
            throw new CourseFullException("Course '" + course.getCourseName() + "' is full. No seats available.");
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
