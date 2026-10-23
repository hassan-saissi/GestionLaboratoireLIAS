package com.lias.servlets;

import com.lias.dao.MembreDAO;
import com.lias.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDAO membreDAO;

    public void init() {
        membreDAO = new MembreDAO();
    }

    // Display the login form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/public/login.jsp").forward(request, response);
    }

    // Process login credentials
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Membre membre = membreDAO.authenticate(email, password);

        if (membre != null) {
            // Authentication successful: create session
            HttpSession session = request.getSession();
            session.setAttribute("userSession", membre);

            // 2. Fetch their role using a simple DAO method and store it in session
            String roleMembre = membreDAO.getRoleByMembreId(membre.getId()); 
            session.setAttribute("userRole", roleMembre); // Ex: "ADMIN", "DIRECTEUR", etc.
            
            // Récupérer l'URL demandée initialement avant le filtre d'authentification
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");

            if (redirectUrl != null) {
                // Supprimer l'attribut de la session pour nettoyer
                session.removeAttribute("redirectAfterLogin");
                // Rediriger vers la page d'origine (ex: /director/validations)
                response.sendRedirect(redirectUrl);
            } else {
                // Redirection par défaut si connexion directe depuis /login
                response.sendRedirect(request.getContextPath() + "/member/espace");
            }
        } else {
            // Authentication failed: redirect back with error message
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect, ou compte inactif.");
            request.getRequestDispatcher("/WEB-INF/jsp/public/login.jsp").forward(request, response);
        }
    }
}