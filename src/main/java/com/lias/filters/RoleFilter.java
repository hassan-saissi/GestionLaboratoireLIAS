package com.lias.filters;

import com.lias.dao.MembreDAO;
import com.lias.models.Membre;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/director/*"})
public class RoleFilter implements Filter {
    private MembreDAO membreDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        membreDAO = new MembreDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        Membre user = (session != null) ? (Membre) session.getAttribute("userSession") : null;
        String requestURI = httpRequest.getRequestURI();

        if (user != null) {
            // 1. Protection de l'espace Administrateur
            if (requestURI.contains("/admin/")) {
                boolean isAdmin = membreDAO.hasRole(user.getId(), "ADMIN");
                if (!isAdmin) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsp");
                    return;
                }
            }

            // 2. Protection de l'espace Directeur
            if (requestURI.contains("/director/")) {
                boolean isDirector = membreDAO.hasRole(user.getId(), "DIRECTEUR") || membreDAO.hasRole(user.getId(), "VICE_DIRECTEUR1");
                if (!isDirector) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsp");
                    return;
                }
            }
        } else {
            // Non connecté
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        // L'utilisateur possède le rôle requis, on laisse passer
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}