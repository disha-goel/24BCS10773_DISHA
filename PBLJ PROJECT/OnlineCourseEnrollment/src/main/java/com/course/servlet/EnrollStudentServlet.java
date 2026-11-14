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

@WebServlet("/enrollStudent")
public class EnrollStudentServlet extends HttpServlet {

    private CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/enroll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentIdParam = request.getParameter("studentId");
        String studentNameParam = request.getParameter("studentName");
        String courseIdParam = request.getParameter("courseId");

        try {
            int studentId = Integer.parseInt(studentIdParam);
            int courseId = Integer.parseInt(courseIdParam);

            courseService.enrollStudent(studentId, studentNameParam, courseId);

            String studentDisplayName = studentNameParam != null ? studentNameParam.trim() : "Student";
            request.setAttribute("successMessage", "Registration confirmed! " + studentDisplayName + " has been successfully enrolled.");
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid numeric format: Student ID and Course ID must be numbers.");
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
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
            request.setAttribute("errorMessage", "System error while processing enrollment: " + e.getMessage());
            request.getRequestDispatcher("/enroll.jsp").forward(request, response);
        }
    }
}
