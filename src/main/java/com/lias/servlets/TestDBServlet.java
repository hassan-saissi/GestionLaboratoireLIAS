package com.lias.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lias.dao.DBConnection;

@WebServlet("/test-db")
public class TestDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Database Connection Test</h2>");
        
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                out.println("<h3 style='color: green;'>SUCCESS: Connected to the database successfully!</h3>");
                out.println("<p>Database Product Name: " + conn.getMetaData().getDatabaseProductName() + "</p>");
                out.println("<p>Database Version: " + conn.getMetaData().getDatabaseProductVersion() + "</p>");
            } else {
                out.println("<h3 style='color: red;'>ERROR: Connection is null or closed.</h3>");
            }
        } catch (SQLException e) {
            out.println("<h3 style='color: red;'>EXCEPTION: Failed to connect!</h3>");
            out.println("<pre>" + e.getMessage() + "</pre>");
        }
        
        out.println("</body></html>");
    }
}