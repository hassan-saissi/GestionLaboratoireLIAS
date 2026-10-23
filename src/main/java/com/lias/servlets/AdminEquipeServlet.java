package com.lias.servlets;

import com.lias.dao.EquipeDAO;
import com.lias.models.Equipe;
import com.lias.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    "/admin/equipes",
    "/admin/equipes/add",
    "/admin/equipes/delete",
    "/admin/equipes/membres"
})
public class AdminEquipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EquipeDAO equipeDAO;

    @Override
    public void init() {
        equipeDAO = new EquipeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin/equipes/delete".equals(path)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                equipeDAO.deleteEquipe(Long.parseLong(idStr));
                request.getSession().setAttribute("successMessage", "Équipe supprimée avec succès.");
            }
            response.sendRedirect(request.getContextPath() + "/admin/equipes");
            return;
        } 
        else if ("/admin/equipes/membres".equals(path)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                Long equipeId = Long.parseLong(idStr);
                Equipe equipe = equipeDAO.getEquipeById(equipeId);
                List<Membre> membres = equipeDAO.getMembresByEquipe(equipeId);

                request.setAttribute("equipe", equipe);
                request.setAttribute("membresEquipe", membres);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/equipe-membres.jsp").forward(request, response);
                return;
            }
        }

        // Affichage par défaut : Liste de toutes les équipes
        List<Equipe> equipes = equipeDAO.getAllEquipes();
        request.setAttribute("equipes", equipes);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/equipes-management.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if ("/admin/equipes/add".equals(path)) {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");

            Equipe equipe = new Equipe();
            equipe.setNomEquipe(nom);
            equipe.setDescription(description);

            boolean success = equipeDAO.ajouterEquipe(equipe);
            if (success) {
                request.getSession().setAttribute("successMessage", "Équipe ajoutée avec succès !");
            } else {
                request.getSession().setAttribute("errorMessage", "Erreur lors de l'ajout de l'équipe.");
            }
            response.sendRedirect(request.getContextPath() + "/admin/equipes");
        }
    }
}