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

        String courseName = request.getParameter("courseName");
        String maxSeatsStr = request.getParameter("maxSeats");

        try {
            int maxSeats = Integer.parseInt(maxSeatsStr);
            courseService.addCourse(courseName, maxSeats);
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
