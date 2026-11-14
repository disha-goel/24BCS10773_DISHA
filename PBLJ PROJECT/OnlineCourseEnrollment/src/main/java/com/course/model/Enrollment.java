package com.course.model;

/**
 * Model class representing an Enrollment entity.
 * Links a Student to a Course.
 */
public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;

    // Extra fields for display purposes (joined from other tables)
    private String studentName;
    private String courseName;

    // Default constructor
    public Enrollment() {}

    // Parameterized constructor
    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters and Setters
    public int getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    // Display method
    public String display() {
        return "Enrollment [ID=" + enrollmentId + ", StudentID=" + studentId +
               ", CourseID=" + courseId + "]";
    }

    @Override
    public String toString() {
        return display();
    }
}
