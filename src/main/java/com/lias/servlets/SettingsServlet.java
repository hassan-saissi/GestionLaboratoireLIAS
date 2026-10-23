package com.lias.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/settings")
public class SettingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to settings view
        request.getRequestDispatcher("/WEB-INF/jsp/admin/settings.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Retrieve settings parameters from form
        String labName = request.getParameter("labName");
        String labEmail = request.getParameter("labEmail");
        String academicYear = request.getParameter("academicYear");

        // Here you can save them to database or servlet context/application attributes
        getServletContext().setAttribute("labName", labName);
        getServletContext().setAttribute("labEmail", labEmail);
        getServletContext().setAttribute("academicYear", academicYear);

        request.setAttribute("successMessage", "Paramètres mis à jour avec succès !");
        request.getRequestDispatcher("/WEB-INF/jsp/admin/settings.jsp").forward(request, response);
    }
}