package com.course.servlet;

import com.course.service.CourseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet that handles adding a new course.
 * GET  -> shows the addCourse.jsp form
 * POST -> processes the form and saves the course
 */
@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simply forward to the add course form
        request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseName = request.getParameter("courseName");
        String maxSeatsStr = request.getParameter("maxSeats");

        try {
            // Validate maxSeats is a number
            int maxSeats = Integer.parseInt(maxSeatsStr);

            // Delegate to service layer
            courseService.addCourse(courseName, maxSeats);

            // Success: redirect to view courses
            response.sendRedirect(request.getContextPath() + "/viewCourses");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Max seats must be a valid number.");
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
        }
    }
}
