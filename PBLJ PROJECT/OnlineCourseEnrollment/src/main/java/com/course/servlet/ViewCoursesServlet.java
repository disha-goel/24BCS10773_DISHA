package com.course.servlet;

import com.course.model.Course;
import com.course.model.Enrollment;
import com.course.service.CourseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that fetches all courses and enrollments and forwards to viewCourses.jsp.
 */
@WebServlet("/viewCourses")
public class ViewCoursesServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Course> courses         = courseService.viewCourses();
            List<Enrollment> enrollments = courseService.viewEnrollments();

            // Set as request attributes so JSP can access them
            request.setAttribute("courses", courses);
            request.setAttribute("enrollments", enrollments);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not load data: " + e.getMessage());
        }

        request.getRequestDispatcher("/viewCourses.jsp").forward(request, response);
    }
}
