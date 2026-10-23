<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demande d'Adhésion - Laboratoire LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 40px; }
        .form-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 600px; margin: auto; }
        h2 { color: #004085; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input[type="text"], input[type="email"], textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        textarea { resize: vertical; height: 120px; }
        button { width: 100%; padding: 12px; background-color: #004085; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #002752; }
        .success { color: #155724; background: #d4edda; border: 1px solid #c3e6cb; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .error { color: #721c24; background: #f8d7da; border: 1px solid #f5c6cb; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .btn-back { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Rejoindre le Laboratoire LIAS</h2>
        <p style="text-align: center; color: #666; margin-bottom: 20px;">Remplissez ce formulaire pour soumettre votre candidature au directeur.</p>
        
        <c:if test="${not empty successMessage}">
            <div class="success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>
        
        <form action="adhesion" method="post">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </div>
            
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email Académique / Professionnel :</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="etablissement">Établissement / Université :</label>
                <input type="text" id="etablissement" name="etablissement" required>
            </div>
            
            <div class="form-group">
                <label for="lettreMotivation">Lettre de Motivation / CV résume :</label>
                <textarea id="lettreMotivation" name="lettreMotivation" required></textarea>
            </div>
            
            <div class="form-group">
    			<label for="cvURL">Lien du CV (Google Drive, LinkedIn, etc.) :</label>
    			<input type="text" id="cvURL" name="cvURL" placeholder="https://..." required>
			</div>
            
            <button type="submit">Soumettre ma candidature</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/login" class="btn-back">← Retour à la connexion</a>
    </div>

</body>
</html>