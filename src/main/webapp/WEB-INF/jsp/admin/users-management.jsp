<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs - Admin LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        .header-flex { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        h2 { color: #004085; margin: 0; }
        .btn-add { background-color: #28a745; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; font-weight: bold; }
        .btn-add:hover { background-color: #218838; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; vertical-align: middle; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn-delete { background-color: #dc3545; color: white; padding: 6px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; }
        .btn-delete:hover { background-color: #c82333; }
        .form-affecter { display: flex; gap: 5px; align-items: center; margin: 0; }
        .form-affecter select { padding: 5px; border-radius: 3px; border: 1px solid #ccc; }
        .btn-affecter { background-color: #004085; color: white; padding: 6px 10px; border: none; border-radius: 3px; cursor: pointer; font-size: 13px; }
        .btn-affecter:hover { background-color: #002752; }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />
    <div class="container">
        
        <!-- Affichage des messages de succès ou d'erreur -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div style="background-color: #d4edda; color: #155724; padding: 12px; margin-bottom: 20px; border-radius: 4px; border: 1px solid #c3e6cb;">
                ${sessionScope.successMessage}
            </div>
            <% session.removeAttribute("successMessage"); %>
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <div style="background-color: #f8d7da; color: #721c24; padding: 12px; margin-bottom: 20px; border-radius: 4px; border: 1px solid #f5c6cb;">
                ${sessionScope.errorMessage}
            </div>
            <% session.removeAttribute("errorMessage"); %>
        </c:if>

        <div class="header-flex">
            <h2>Panneau Admin : Gestion des Utilisateurs</h2>
            <a href="${pageContext.request.contextPath}/admin/create-user" class="btn-add">+ Ajouter un Membre</a>
        </div>

        <!-- BARRE DE RECHERCHE -->
        <form action="${pageContext.request.contextPath}/admin/users" method="get" style="margin: 20px 0; display: flex; gap: 10px; align-items: center;">
            <input type="text" name="search" value="${searchQuery}" placeholder="Rechercher par nom, prénom ou email..." 
                   style="padding: 8px; width: 320px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px;">
            <button type="submit" style="padding: 8px 15px; background-color: #004085; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">
                Rechercher
            </button>
            <c:if test="${not empty searchQuery}">
                <a href="${pageContext.request.contextPath}/admin/users" style="padding: 8px 15px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; font-size: 14px;">
                    Réinitialiser
                </a>
            </c:if>
        </form>
        
        
        <table>
            <tr>
                <th>ID</th>
                <th>Nom & Prénom</th>
                <th>Email</th>
                <th>Statut</th>
                <th>Établissement</th>
                <th>Affecter à une équipe</th> <!-- Nouvelle colonne -->
                <th>Actions</th>
            </tr>
            <c:forEach var="m" items="${membres}">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.nom} ${m.prenom}</td>
                    <td>${m.email}</td>
                    <td>${m.statut}</td>
                    <td>${m.etablissementOrigine}</td>
                    
                    <!-- Colonne pour choisir et assigner une équipe -->
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/membre/affecter" method="post" class="form-affecter">
                            <input type="hidden" name="membreId" value="${m.id}">
                            <select name="equipeId" required>
                                <option value="">-- Choisir une équipe --</option>
                                <c:forEach var="equip" items="${toutesLesEquipes}">
                                    <option value="${equip.id}">${equip.nomEquipe}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn-affecter">Affecter</button>
                        </form>
                    </td>

                    <!-- Colonne des actions (Modifier / Supprimer) -->
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/users/edit?id=${m.id}" 
                           style="background-color: #ffc107; color: black; padding: 6px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; margin-right: 5px;">
                           Modifier
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/users/delete?id=${m.id}" 
                           class="btn-delete" 
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce membre ?');">
                           Supprimer
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>