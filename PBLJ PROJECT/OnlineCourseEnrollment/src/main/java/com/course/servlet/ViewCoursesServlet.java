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

@WebServlet("/viewCourses")
public class ViewCoursesServlet extends HttpServlet {

    private CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Course> courseList = courseService.viewCourses();
            List<Enrollment> enrollmentList = courseService.viewEnrollments();

            request.setAttribute("courses", courseList);
            request.setAttribute("enrollments", enrollmentList);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error fetching records: " + e.getMessage());
        }

        request.getRequestDispatcher("/viewCourses.jsp").forward(request, response);
    }
}
