<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Laboratoire LIAS - Accueil Public</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; }
        .header { background-color: #004085; color: white; padding: 20px; border-radius: 6px; display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .header h1 { margin: 0; font-size: 24px; }
        .nav-links a { color: #ffc107; text-decoration: none; margin-left: 15px; font-weight: bold; }
        .container { display: flex; flex-direction: column; gap: 20px; }
        .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        h3 { color: #004085; border-bottom: 2px solid #004085; padding-bottom: 5px; margin-top: 0; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; font-size: 14px; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 4px; font-size: 13px; }
        .form-group input, .form-group textarea, .form-group select { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #28a745; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }
        .btn-submit:hover { background-color: #218838; }
        .badge { background: #17a2b8; color: white; padding: 3px 8px; border-radius: 4px; font-size: 12px; display: inline-block; }
        .alert-success { background-color: #d4edda; color: #155724; padding: 10px; border-radius: 4px; margin-bottom: 15px; }
    </style>
</head>
<body>

    <div class="header">
        <h1>Laboratoire de Recherche LIAS</h1>
        <div class="nav-links">
            <c:choose>
                <c:when test="${not empty sessionScope.userSession}">
                    <a href="${pageContext.request.contextPath}/member/espace">Mon Espace</a>
                    <a href="${pageContext.request.contextPath}/logout" style="color: #dc3545;">Déconnexion</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login">Connexion</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="container">

        <!-- Messages de succès (ex: demande d'adhésion envoyée) -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert-success">
                ${sessionScope.successMessage}
            </div>
            <% session.removeAttribute("successMessage"); %>
        </c:if>

        <!-- SECTION 1 : ÉQUIPES DE RECHERCHE -->
        <div class="card">
            <h3>Nos Équipes de Recherche</h3>
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 15px; margin-top: 10px;">
                <c:forEach var="equip" items="${listeEquipes}">
                    <div style="background: #fdfdfd; border: 1px solid #e3e3e3; padding: 15px; border-radius: 6px;">
                        <strong style="color: #004085; font-size: 16px;">${equip.nomEquipe}</strong>
                        <p style="font-size: 13px; color: #555; margin: 8px 0;">${equip.description}</p>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- SECTION 2 : PUBLICATIONS SCIENTIFIQUES PUBLIQUES -->
        <div class="card">
            <h3>Publications Scientifiques Récentes</h3>
            <table>
                <tr>
                    <th>Année</th>
                    <th>Type</th>
                    <th>Titre</th>
                    <th>Résumé / Auteur</th>
                </tr>
                <c:forEach var="pub" items="${listePublications}">
                    <tr>
                        <td><strong>${pub.annee}</strong></td>
                        <td><span class="badge">${pub.typePublication}</span></td>
                        <td>${pub.titre}</td>
                        <td>${pub.resume}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listePublications}">
                    <tr><td colspan="4" style="text-align: center; color: #666;">Aucune publication disponible pour le moment.</td></tr>
                </c:if>
            </table>
        </div>

        <!-- SECTION 3 : DEMANDE D'ADHÉSION AU LABORATOIRE -->
        <div class="card">
            <h3>Rejoindre le Laboratoire (Demande d'Adhésion)</h3>
            <p style="font-size: 13px; color: #666; margin-bottom: 15px;">Vous êtes chercheur ou étudiant et souhaitez intégrer le laboratoire LIAS ? Cliquez ci-dessous pour accéder au formulaire dédié :</p>
            
            <a href="${pageContext.request.contextPath}/adhesion" style="display: inline-block; background-color: #28a745; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold;">
                Accéder au formulaire d'adhésion
            </a>
        </div>

    </div>
</body>
</html>