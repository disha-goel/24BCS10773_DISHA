package com.course.model;

/**
 * Model class representing a Course entity.
 */
public class Course {

    private int courseId;
    private String courseName;
    private int maxSeats;
    private int enrolledStudents;

    // Default constructor
    public Course() {}

    // Parameterized constructor
    public Course(int courseId, String courseName, int maxSeats, int enrolledStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxSeats = maxSeats;
        this.enrolledStudents = enrolledStudents;
    }

    // Constructor without courseId (for insert)
    public Course(String courseName, int maxSeats) {
        this.courseName = courseName;
        this.maxSeats = maxSeats;
        this.enrolledStudents = 0;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getMaxSeats() { return maxSeats; }
    public void setMaxSeats(int maxSeats) { this.maxSeats = maxSeats; }

    public int getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(int enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    // Available seats helper
    public int getAvailableSeats() {
        return maxSeats - enrolledStudents;
    }

    // Display method
    public String display() {
        return "Course [ID=" + courseId + ", Name=" + courseName +
               ", MaxSeats=" + maxSeats + ", Enrolled=" + enrolledStudents +
               ", Available=" + getAvailableSeats() + "]";
    }

    @Override
    public String toString() {
        return display();
    }
}
