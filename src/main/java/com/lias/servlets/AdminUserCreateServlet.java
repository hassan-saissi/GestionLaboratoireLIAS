package com.lias.servlets;

import com.lias.dao.MembreDAO;
import com.lias.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/create-user")
public class AdminUserCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDAO membreDAO;

    public void init() {
        membreDAO = new MembreDAO();
    }

    // Display the creation form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin/user-create.jsp").forward(request, response);
    }

    // Process form data and save to database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String statut = request.getParameter("statut");
        String etablissement = request.getParameter("etablissementOrigine");
        String labo = request.getParameter("laboratoireOrigine");

        Membre membre = new Membre();
        membre.setNom(nom);
        membre.setPrenom(prenom);
        membre.setEmail(email);
        membre.setPassword(password);
        membre.setStatut(statut);
        membre.setEtablissementOrigine(etablissement);
        membre.setLaboratoireOrigine(labo);
        membre.setCompteActif(true); // Active by default when created by admin

        boolean isCreated = membreDAO.insertMembre(membre);

        if (isCreated) {
            // Redirect back to the Admin Users Management page
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            // Return to form with error message
            request.setAttribute("errorMessage", "Erreur lors de la création. Vérifiez si l'email existe déjà.");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/user-create.jsp").forward(request, response);
        }
    }
}