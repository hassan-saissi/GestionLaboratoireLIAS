package com.lias.servlets;

import com.lias.dao.DemandeAdhesionDAO;
import com.lias.models.DemandeAdhesion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    "/directeur/demandes",
    "/directeur/demandes/accepter",
    "/directeur/demandes/rejeter",
    "/director/validations"
})
public class AdminDemandesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DemandeAdhesionDAO demandeDAO;

    @Override
    public void init() {
        demandeDAO = new DemandeAdhesionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();

        // 1. Action : Accepter la demande
        if ("/directeur/demandes/accepter".equals(path)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                boolean success = demandeDAO.updateStatutDemande(Long.parseLong(idStr), "Acceptee");
                if (success) {
                    request.getSession().setAttribute("successMessage", "Demande acceptée, compte créé et e-mail envoyé avec succès !");
                } else {
                    request.getSession().setAttribute("errorMessage", "Erreur lors de l'acceptation de la demande.");
                }
            }
            response.sendRedirect(request.getContextPath() + "/directeur/demandes");
            return;
        } 
        // 2. Action : Rejeter la demande
        else if ("/directeur/demandes/rejeter".equals(path)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                boolean success = demandeDAO.updateStatutDemande(Long.parseLong(idStr), "Rejetee");
                if (success) {
                    request.getSession().setAttribute("successMessage", "Demande rejetée avec succès.");
                } else {
                    request.getSession().setAttribute("errorMessage", "Erreur lors du rejet de la demande.");
                }
            }
            response.sendRedirect(request.getContextPath() + "/directeur/demandes");
            return;
        }

        // 3. Affichage par défaut (Liste des demandes en attente + Recherche)
        String searchQuery = request.getParameter("search");
        List<DemandeAdhesion> demandes;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            demandes = demandeDAO.rechercherDemandes(searchQuery, "EN_ATTENTE");
        } else {
            demandes = demandeDAO.getDemandesParStatut("EN_ATTENTE");
        }

        request.setAttribute("demandes", demandes);
        request.setAttribute("searchQuery", searchQuery);
        
        // Redirection vers votre page JSP de validation
        request.getRequestDispatcher("/WEB-INF/jsp/director/validations.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}