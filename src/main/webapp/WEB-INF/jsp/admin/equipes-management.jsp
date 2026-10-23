<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Équipes - Admin LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h2, h3 { color: #004085; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; vertical-align: middle; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn-action { padding: 6px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; font-weight: bold; color: white; margin-right: 5px; }
        .btn-members { background-color: #17a2b8; }
        .btn-members:hover { background-color: #138496; }
        .btn-delete { background-color: #dc3545; }
        .btn-delete:hover { background-color: #c82333; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
        .form-group input, .form-group textarea { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        .btn-submit { background-color: #28a745; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }
        .btn-submit:hover { background-color: #218838; }
        .card { background: #fdfdfd; border: 1px solid #e3e3e3; padding: 20px; border-radius: 6px; margin-bottom: 25px; }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />
    <div class="container">

        <!-- Messages de succès -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div style="background-color: #d4edda; color: #155724; padding: 12px; margin-bottom: 20px; border-radius: 4px;">
                ${sessionScope.successMessage}
            </div>
            <% session.removeAttribute("successMessage"); %>
        </c:if>

        <h2>Gestion des Équipes du Laboratoire</h2>
        <p><a href="${pageContext.request.contextPath}/admin/dashboard" style="color: #004085; text-decoration: none; font-weight: bold;">← Retour au Tableau de bord</a></p>

        <!-- Formulaire d'ajout d'une équipe -->
        <div class="card">
            <h3>Ajouter une nouvelle équipe</h3>
            <form action="${pageContext.request.contextPath}/admin/equipes/add" method="post">
                <div class="form-group">
                    <label>Nom de l'équipe :</label>
                    <input type="text" name="nom" required placeholder="Ex: IA et Systèmes Intelligents">
                </div>
                <div class="form-group">
                    <label>Description :</label>
                    <textarea name="description" rows="3" placeholder="Courte description des axes de recherche..."></textarea>
                </div>
                <button type="submit" class="btn-submit">+ Enregistrer l'équipe</button>
            </form>
        </div>

        <!-- Liste des équipes -->
        <h3>Liste des Équipes Existantes</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Nom de l'équipe</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="equip" items="${equipes}">
                <tr>
                    <td>${equip.id}</td>
                    <td><strong>${equip.nomEquipe}</strong></td>
                    <td>${equip.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/equipes/membres?id=${equip.id}" class="btn-action btn-members">Membres</a>
                        <a href="${pageContext.request.contextPath}/admin/equipes/delete?id=${equip.id}" class="btn-action btn-delete" onclick="return confirm('Voulez-vous vraiment supprimer cette équipe ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty equipes}">
                <tr>
                    <td colspan="4" style="text-align: center; color: #666;">Aucune équipe enregistrée pour le moment.</td>
                </tr>
            </c:if>
        </table>
    </div>
</body>
</html>