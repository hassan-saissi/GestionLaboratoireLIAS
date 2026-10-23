<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Membres de l'équipe - ${equipe.nomEquipe}</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h2 { color: #004085; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn-back { display: inline-block; margin-bottom: 20px; background-color: #6c757d; color: white; padding: 8px 12px; text-decoration: none; border-radius: 4px; font-weight: bold; }
        .btn-back:hover { background-color: #5a6268; }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />
    <div class="container">
        <a href="${pageContext.request.contextPath}/admin/equipes" class="btn-back">← Retour à la gestion des équipes</a>
        
        <h2>Membres actifs de l'équipe : <span style="color: #28a745;">${equipe.nomEquipe}</span></h2>
        <p><em>${equipe.description}</em></p>

        <table>
            <tr>
                <th>ID</th>
                <th>Nom & Prénom</th>
                <th>Email</th>
                <th>Statut</th>
            </tr>
            <c:forEach var="m" items="${membresEquipe}">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.nom} ${m.prenom}</td>
                    <td>${m.email}</td>
                    <td>${m.statut}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty membresEquipe}">
                <tr>
                    <td colspan="4" style="text-align: center; color: #666;">Aucun membre n'est actuellement affecté à cette équipe.</td>
                </tr>
            </c:if>
        </table>
    </div>
</body>
</html>