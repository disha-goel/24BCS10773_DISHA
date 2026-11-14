package com.course.servlet;

import com.course.exception.CourseFullException;
import com.course.exception.CourseNotFoundException;
import com.course.exception.DuplicateEnrollmentException;
import com.course.service.CourseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet that handles student enrollment into a course.
 * GET  -> shows the enroll.jsp form
 * POST -> processes enrollment
 */
@WebServlet("/enrollStudent")
public class EnrollStudentServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/enroll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentIdStr = request.getParameter("studentId");
        String studentName  = request.getParameter("studentName");
        String courseIdStr  = request.getParameter("courseId");

        try {
            // Parse IDs
            int studentId = Integer.parseInt(studentIdStr);
            int courseId  = Integer.parseInt(courseIdStr);

            // Delegate to service layer (all business logic lives there)
            courseService.enrollStudent(studentId, studentName, courseId);

            // Success
            request.setAttribute("successMessage",
                "Student '" + studentName + "' enrolled successfully!");
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Student ID and Course ID must be valid numbers.");
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (CourseNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (DuplicateEnrollmentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (CourseFullException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);
        }
    }
}
