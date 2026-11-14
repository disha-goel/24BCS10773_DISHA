package com.course.model;

/**
 * Model class representing a Student entity.
 */
public class Student {

    private int studentId;
    private String studentName;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    // Display method
    public String display() {
        return "Student [ID=" + studentId + ", Name=" + studentName + "]";
    }

    @Override
    public String toString() {
        return display();
    }
}
