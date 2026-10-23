package com.lias.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/member/*", "/director/*", "/admin/*"})
public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("userSession") != null);

        if (isLoggedIn) {
            chain.doFilter(request, response); // Autoriser l'accès
        } else {
            // 1. Récupérer l'URL complète demandée au départ (avec ses paramètres de requête si présents)
            String targetUrl = httpRequest.getRequestURI();
            if (httpRequest.getQueryString() != null) {
                targetUrl += "?" + httpRequest.getQueryString();
            }

            // 2. Créer/Récupérer la session pour stocker l'URL cible
            HttpSession currentSession = httpRequest.getSession(true);
            currentSession.setAttribute("redirectAfterLogin", targetUrl);

            // 3. Rediriger vers la page de connexion
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    public void destroy() {}
}