package com.course.exception;

/**
 * Exception thrown when a course is not found in the database.
 */
public class CourseNotFoundException extends Exception {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
