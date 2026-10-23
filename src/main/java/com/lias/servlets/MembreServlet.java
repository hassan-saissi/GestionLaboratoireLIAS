package com.lias.servlets;

import com.lias.dao.MembreDAO;
import com.lias.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/membres")
public class MembreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDAO membreDAO;

    public void init() {
        membreDAO = new MembreDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Fetch list of members from database
        List<Membre> listeMembres = membreDAO.getAllMembres();
        
        // Attach list to request attribute
        request.setAttribute("membres", listeMembres);
        
        // Forward to JSP page for display
        request.getRequestDispatcher("/WEB-INF/jsp/member/membres-list.jsp").forward(request, response);
    }
}