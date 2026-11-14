package com.course.exception;

/**
 * Exception thrown when a course has no available seats.
 */
public class CourseFullException extends Exception {

    public CourseFullException(String message) {
        super(message);
    }
}
