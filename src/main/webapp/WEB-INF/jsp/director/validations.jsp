<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Validation des Demandes - Directeur LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h2 { color: #004085; margin-top: 0; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; vertical-align: middle; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn-accept { background-color: #28a745; color: white; padding: 6px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; font-weight: bold; }
        .btn-accept:hover { background-color: #218838; }
        .btn-reject { background-color: #dc3545; color: white; padding: 6px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; font-weight: bold; }
        .btn-reject:hover { background-color: #c82333; }
        .btn-link { color: #004085; text-decoration: underline; font-weight: bold; }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />
    <div class="container">

        <!-- Messages de succès / erreur -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div style="background-color: #d4edda; color: #155724; padding: 12px; margin-bottom: 20px; border-radius: 4px;">
                ${sessionScope.successMessage}
            </div>
            <% session.removeAttribute("successMessage"); %>
        </c:if>

        <h2>Validation des Demandes d'Adhésion en Attente</h2>

        <!-- Barre de recherche -->
        <form action="${pageContext.request.contextPath}/directeur/demandes" method="get" style="margin: 20px 0; display: flex; gap: 10px; align-items: center;">
            <input type="text" name="search" value="${searchQuery}" placeholder="Rechercher par nom, prénom ou email..." 
                   style="padding: 8px; width: 320px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">
            <button type="submit" style="padding: 8px 15px; background-color: #004085; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">
                Rechercher
            </button>
            <c:if test="${not empty searchQuery}">
                <a href="${pageContext.request.contextPath}/directeur/demandes" style="padding: 8px 15px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; font-size: 14px;">
                    Réinitialiser
                </a>
            </c:if>
        </form>

        <table>
            <tr>
                <th>ID</th>
                <th>Nom & Prénom</th>
                <th>Email</th>
                <th>Établissement</th>
                <th>CV</th>
                <th>Lettre de Motivation</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="d" items="${demandes}">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.nom} ${d.prenom}</td>
                    <td>${d.email}</td>
                    <td>${d.etablissement}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty d.cvURL}">
                                <a href="${d.cvURL}" target="_blank" class="btn-link">Check out the CV</a>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #888;">Awan</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty d.lettreMotivation}">
                                <span title="${d.lettreMotivation}">
                                    ${d.lettreMotivation.length() > 30 ? d.lettreMotivation.substring(0, 30).concat('...') : d.lettreMotivation}
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #888;">Awan</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/directeur/demandes/accepter?id=${d.id}" 
                           class="btn-accept" 
                           onclick="return confirm('Voulez-vous accepter cette demande ? Un mot de passe sera généré et envoyé par e-mail.');">
                           Accepter
                        </a>
                        <a href="${pageContext.request.contextPath}/directeur/demandes/rejeter?id=${d.id}" 
                           class="btn-reject" 
                           onclick="return confirm('Êtes-vous sûr de vouloir rejeter cette demande ?');">
                           Rejeter
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty demandes}">
                <tr>
                    <td colspan="7" style="text-align: center; color: #666;">Aucune demande d'adhésion en attente.</td>
                </tr>
            </c:if>
        </table>
    </div>
</body>
</html>