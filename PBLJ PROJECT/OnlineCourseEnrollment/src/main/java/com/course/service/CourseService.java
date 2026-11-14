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

/**
 * Service class containing business logic for the enrollment system.
 */
public class CourseService {

    private final CourseDAO courseDAO       = new CourseDAO();
    private final StudentDAO studentDAO     = new StudentDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    /**
     * Adds a new course after basic validation.
     *
     * @param courseName name of the course
     * @param maxSeats   maximum number of seats
     * @throws SQLException on DB error
     * @throws IllegalArgumentException if input is invalid
     */
    public void addCourse(String courseName, int maxSeats)
            throws SQLException, IllegalArgumentException {

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Max seats must be greater than 0.");
        }

        Course course = new Course(courseName.trim(), maxSeats);
        courseDAO.addCourse(course);
    }

    /**
     * Enrolls a student into a course.
     * Business rules:
     *   1. Course must exist.
     *   2. Student must not already be enrolled.
     *   3. Course must have available seats.
     *
     * @param studentId   ID of the student
     * @param studentName name of the student
     * @param courseId    ID of the course
     * @throws SQLException                on DB error
     * @throws CourseNotFoundException     if course does not exist
     * @throws DuplicateEnrollmentException if student is already enrolled
     * @throws CourseFullException         if no seats are available
     */
    public void enrollStudent(int studentId, String studentName, int courseId)
            throws SQLException, CourseNotFoundException,
                   DuplicateEnrollmentException, CourseFullException {

        // 1. Check course exists
        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            throw new CourseNotFoundException(
                "Course with ID " + courseId + " was not found.");
        }

        // 2. Check duplicate enrollment
        boolean isDuplicate = enrollmentDAO.checkDuplicateEnrollment(studentId, courseId);
        if (isDuplicate) {
            throw new DuplicateEnrollmentException(
                "Student " + studentName + " is already enrolled in " + course.getCourseName() + ".");
        }

        // 3. Check seat availability
        if (course.getEnrolledStudents() >= course.getMaxSeats()) {
            throw new CourseFullException(
                "Course '" + course.getCourseName() + "' is full. No seats available.");
        }

        // 4. Ensure student record exists
        studentDAO.getOrCreateStudent(studentId, studentName);

        // 5. Create enrollment and update seat count
        enrollmentDAO.enrollStudent(studentId, courseId);
        courseDAO.incrementEnrolledCount(courseId);
    }

    /**
     * Returns all courses from the database.
     *
     * @return list of Course objects
     * @throws SQLException on DB error
     */
    public List<Course> viewCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    /**
     * Returns all enrollments with student and course details.
     *
     * @return list of Enrollment objects
     * @throws SQLException on DB error
     */
    public List<Enrollment> viewEnrollments() throws SQLException {
        return enrollmentDAO.getAllEnrollments();
    }
}
