<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav style="background-color: #004085; padding: 12px 20px; color: white; display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; border-radius: 4px;">
    
    <div style="display: flex; align-items: center; gap: 20px;">
        <c:choose>
            <%-- Check the session attribute 'userRole' directly --%>
            <c:when test="${fn:toUpperCase(userRole) == 'ADMIN'}">
                <span style="font-weight: bold; font-size: 16px;">LIAS Admin Panel</span>
                <a href="${pageContext.request.contextPath}/admin/dashboard" style="color: white; text-decoration: none; font-size: 14px;">Tableau de Bord</a>
                <a href="${pageContext.request.contextPath}/admin/equipes" style="color: white; text-decoration: none; font-size: 14px;">Gestion des Équipes</a>
            </c:when>

            <c:when test="${fn:toUpperCase(userRole) == 'DIRECTEUR'}">
                <span style="font-weight: bold; font-size: 16px;">LIAS Directeur Panel</span>
                <a href="${pageContext.request.contextPath}/directeur/demandes" style="color: white; text-decoration: none; font-size: 14px;">Validation des Demandes</a>
            </c:when>

            <c:otherwise>
                <span style="font-weight: bold; font-size: 16px;">LIAS Laboratoire</span>
                <a href="${pageContext.request.contextPath}/member/espace" style="color: white; text-decoration: none; font-size: 14px;">Mon Espace</a>
            </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/index.jsp" style="color: #ffc107; text-decoration: none; font-size: 14px;">Voir le Site Public ↗</a>
    </div>

    <div>
        <c:if test="${not empty userSession}">
            <a href="${pageContext.request.contextPath}/logout" style="background-color: #dc3545; color: white; padding: 6px 12px; text-decoration: none; border-radius: 4px; font-size: 13px; font-weight: bold;">
                Déconnexion
            </a>
        </c:if>
    </div>
</nav>