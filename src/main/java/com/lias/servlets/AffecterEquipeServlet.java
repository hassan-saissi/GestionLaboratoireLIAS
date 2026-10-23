package com.lias.servlets;

import com.lias.dao.MembreDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/membre/affecter")
public class AffecterEquipeServlet extends HttpServlet {
    private MembreDAO membreDAO = new MembreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Long membreId = Long.parseLong(request.getParameter("membreId"));
            Long equipeId = Long.parseLong(request.getParameter("equipeId"));

            boolean success = membreDAO.assignerEquipe(membreId, equipeId);

            if (success) {
                request.getSession().setAttribute("successMessage", "Membre affecté à l'équipe avec succès !");
            } else {
                request.getSession().setAttribute("errorMessage", "Affectation refusée : ce membre appartient déjà activement à cette équipe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Une erreur technique est survenue.");
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}