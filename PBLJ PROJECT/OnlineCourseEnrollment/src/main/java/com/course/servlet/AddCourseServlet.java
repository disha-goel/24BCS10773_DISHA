package com.course.servlet;

import com.course.service.CourseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    private CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String courseNameParam = request.getParameter("courseName");
        String maxSeatsParam = request.getParameter("maxSeats");

        try {
            int maxSeats = Integer.parseInt(maxSeatsParam);
            courseService.addCourse(courseNameParam, maxSeats);
            response.sendRedirect(request.getContextPath() + "/viewCourses");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid format: Maximum seats must be an integer.");
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Unable to create course: " + e.getMessage());
            request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
        }
    }
}
