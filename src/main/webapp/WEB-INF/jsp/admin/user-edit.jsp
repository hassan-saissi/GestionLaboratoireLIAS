<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un Membre - Admin LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 40px; }
        .form-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 600px; margin: auto; }
        h2 { color: #004085; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input[type="text"], input[type="email"], select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 12px; background-color: #004085; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #002752; }
        .btn-back { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; }
        .btn-back:hover { text-decoration: underline; }
    </style>
</head>
<body>
	<jsp:include page="../common/navbar.jsp" />
    <div class="form-container">
        <h2>Modifier le Membre</h2>
        
        <form action="edit" method="post">
            <%-- Hidden input to preserve member ID during update --%>
            <input type="hidden" name="id" value="${membre.id}">
            
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" value="${membre.nom}" required>
            </div>
            
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" value="${membre.prenom}" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" value="${membre.email}" required>
            </div>
            
            <div class="form-group">
                <label for="statut">Statut :</label>
                <select id="statut" name="statut" required>
                    <option value="Permanent" ${membre.statut == 'Permanent' ? 'selected' : ''}>Permanent</option>
                    <option value="Associe" ${membre.statut == 'Associe' ? 'selected' : ''}>Associé</option>
                    <option value="Doctorant" ${membre.statut == 'Doctorant' ? 'selected' : ''}>Doctorant</option>
                    <option value="Retraite" ${membre.statut == 'Retraite' ? 'selected' : ''}>Retraité</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="etablissementOrigine">Établissement d'Origine :</label>
                <input type="text" id="etablissementOrigine" name="etablissementOrigine" value="${membre.etablissementOrigine}">
            </div>
            
            <div class="form-group">
                <label for="laboratoireOrigine">Laboratoire d'Origine :</label>
                <input type="text" id="laboratoireOrigine" name="laboratoireOrigine" value="${membre.laboratoireOrigine}">
            </div>
            
            <button type="submit">Enregistrer les modifications</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/admin/users" class="btn-back">← Retour à la liste</a>
    </div>

</body>
</html>