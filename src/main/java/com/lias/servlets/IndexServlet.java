package com.lias.servlets;

import com.lias.dao.EquipeDAO;
import com.lias.dao.PublicationDAO;
import com.lias.models.Equipe;
import com.lias.models.Publication;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index") // ou "/" selon votre configuration
public class IndexServlet extends HttpServlet {
    
    private EquipeDAO equipeDAO;
    private PublicationDAO publicationDAO;

    public void init() {
        equipeDAO = new EquipeDAO();
        publicationDAO = new PublicationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des équipes et des publications depuis la BDD
        List<Equipe> listeEquipes = equipeDAO.getAllEquipes();
        List<Publication> listePublications = publicationDAO.getAllPublications();

        // Les injecter dans la requête
        request.setAttribute("listeEquipes", listeEquipes);
        request.setAttribute("listePublications", listePublications);

        // Rediriger vers la JSP
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}