package com.lias.servlets;

import com.lias.dao.EquipeDAO;
import com.lias.dao.MembreDAO;
import com.lias.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/users", "/admin/users/delete", "/admin/users/edit"})
public class AdminUsersManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDAO membreDAO;

    public void init() {
        membreDAO = new MembreDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getServletPath();

        if ("/admin/users/delete".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                membreDAO.deleteMembre(Long.parseLong(idStr));
            }
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        } 
        else if ("/admin/users/edit".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                Membre membre = membreDAO.getMembreById(Long.parseLong(idStr));
                request.setAttribute("membre", membre);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/user-edit.jsp").forward(request, response);
                return;
            }
        }
        
        // Logique de recherche ou affichage global de la liste
        String searchQuery = request.getParameter("search");
        List<Membre> membres;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            membres = membreDAO.rechercherMembres(searchQuery);
        } else {
            membres = membreDAO.getAllMembres();
        }

        request.setAttribute("membres", membres);
        request.setAttribute("searchQuery", searchQuery); // Pour garder la valeur dans l'input après recherche

        // Charger aussi les équipes pour les listes déroulantes d'affectation
        EquipeDAO equipeDAO = new EquipeDAO();
        request.setAttribute("toutesLesEquipes", equipeDAO.getAllEquipes());

        // Transmission finale vers la vue JSP
        request.getRequestDispatcher("/WEB-INF/jsp/admin/users-management.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if ("/admin/users/edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String statut = request.getParameter("statut");
            String etablissement = request.getParameter("etablissementOrigine");
            String labo = request.getParameter("laboratoireOrigine");

            Membre membre = new Membre();
            membre.setId(id);
            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setEmail(email);
            membre.setStatut(statut);
            membre.setEtablissementOrigine(etablissement);
            membre.setLaboratoireOrigine(labo);

            membreDAO.updateMembre(membre);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}