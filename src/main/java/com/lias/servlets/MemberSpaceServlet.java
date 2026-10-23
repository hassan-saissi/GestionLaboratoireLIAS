package com.lias.servlets;

import com.lias.dao.DemandeMaterielDAO;
import com.lias.dao.EquipeDAO;
import com.lias.dao.PublicationDAO;
import com.lias.models.DemandeMateriel;
import com.lias.models.Equipe;
import com.lias.models.Membre;
import com.lias.models.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {
    "/member/espace",
    "/member/publication/add",
    "/member/materiel/add"
})
public class MemberSpaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PublicationDAO publicationDAO = new PublicationDAO();
    private DemandeMaterielDAO materielDAO = new DemandeMaterielDAO();
    private EquipeDAO equipeDAO = new EquipeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Membre membre = (session != null) ? (Membre) session.getAttribute("userSession") : null;

        if (membre == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Long membreId = membre.getId();

        // 1. Récupérer les équipes du membre
        List<Equipe> mesEquipes = equipeDAO.getEquipesByMembre(membreId);

        // 2. Récupérer les collègues pour chaque équipe (Map<Equipe, List<Membre>>)
        Map<Equipe, List<Membre>> colleguesParEquipe = new HashMap<>();
        for (Equipe eq : mesEquipes) {
            List<Membre> membresEquipe = equipeDAO.getMembresByEquipe(eq.getId());
            colleguesParEquipe.put(eq, membresEquipe);
        }

        // 3. Récupérer les publications et demandes du membre
        List<Publication> mesPublications = publicationDAO.getPublicationsByMembre(membreId);
        List<DemandeMateriel> mesDemandes = materielDAO.getDemandesByMembre(membreId);

        request.setAttribute("mesEquipes", mesEquipes);
        request.setAttribute("colleguesParEquipe", colleguesParEquipe);
        request.setAttribute("mesPublications", mesPublications);
        request.setAttribute("mesDemandes", mesDemandes);

        request.getRequestDispatcher("/WEB-INF/jsp/member/espace-membre.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Membre membre = (session != null) ? (Membre) session.getAttribute("userSession") : null;

        if (membre == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getServletPath();

        if ("/member/publication/add".equals(path)) {
            String titre = request.getParameter("titre");
            String resume = request.getParameter("resume");
            int annee = Integer.parseInt(request.getParameter("annee"));
            String typePublication = request.getParameter("typePublication");
            String equipeIdStr = request.getParameter("equipeId");

            Publication pub = new Publication();
            pub.setTitre(titre);
            pub.setResume(resume);
            pub.setAnnee(annee);
            pub.setTypePublication(typePublication);
            pub.setAuteurMembreId(membre.getId());
            
            if (equipeIdStr != null && !equipeIdStr.isEmpty()) {
                pub.setEquipeId(Long.parseLong(equipeIdStr));
            }

            publicationDAO.ajouterPublication(pub);
            request.getSession().setAttribute("successMessage", "Publication ajoutée avec succès.");
        }
        else if ("/member/materiel/add".equals(path)) {
            String descriptionBesoin = request.getParameter("descriptionBesoin");
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            DemandeMateriel demande = new DemandeMateriel();
            demande.setDescriptionBesoin(descriptionBesoin);
            demande.setQuantite(quantite);
            demande.setMembreId(membre.getId());

            materielDAO.ajouterDemande(demande);
            request.getSession().setAttribute("successMessage", "Demande de matériel envoyée avec succès.");
        }

        response.sendRedirect(request.getContextPath() + "/member/espace");
    }
}
