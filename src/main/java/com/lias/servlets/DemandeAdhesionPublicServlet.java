package com.lias.servlets;

import com.lias.dao.DemandeAdhesionDAO;
import com.lias.models.DemandeAdhesion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/adhesion", "/adhesion/submit"})
public class DemandeAdhesionPublicServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DemandeAdhesionDAO demandeDAO;

    @Override
    public void init() {
        demandeDAO = new DemandeAdhesionDAO();
    }

    // Afficher le formulaire public d'adhésion
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/public/adhesion-form.jsp").forward(request, response);
    }

    // Traiter l'envoi du formulaire par le visiteur
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String etablissement = request.getParameter("etablissement");
        String cvURL = request.getParameter("cvURL");
        String lettreMotivation = request.getParameter("lettreMotivation");

        DemandeAdhesion demande = new DemandeAdhesion();
        demande.setNom(nom);
        demande.setPrenom(prenom);
        demande.setEmail(email);
        demande.setEtablissement(etablissement);
        demande.setCvURL(cvURL);
        demande.setLettreMotivation(lettreMotivation);

        boolean success = demandeDAO.insererDemande(demande);

        if (success) {
            request.setAttribute("successMessage", "Votre demande d'adhésion a été envoyée avec succès au directeur du laboratoire !");
        } else {
            request.setAttribute("errorMessage", "Une erreur est survenue lors de l'envoi de votre demande.");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/public/adhesion-form.jsp").forward(request, response);
    }
}