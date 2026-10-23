<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Membre - Laboratoire LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 40px; }
        .form-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 600px; margin: auto; }
        h2 { color: #004085; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input[type="text"], input[type="email"], input[type="password"], select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 12px; background-color: #004085; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #002752; }
        .error { color: #d9534f; background: #fdf7f7; border: 1px solid #ebccd1; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
    </style>
</head>
<body>
	<jsp:include page="../common/navbar.jsp" />
    <div class="form-container">
        <h2>Ajouter un Nouveau Membre (Admin Panel)</h2>
        
        <%-- Display error message if creation fails --%>
        <if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </if>
        
        <form action="create-user" method="post">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </div>
            
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="password">Mot de passe temporaire :</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="statut">Statut :</label>
                <select id="statut" name="statut" required>
                    <option value="Permanent">Permanent</option>
                    <option value="Associe">Associé</option>
                    <option value="Doctorant">Doctorant</option>
                    <option value="Retraite">Retraité</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="etablissementOrigine">Établissement d'Origine :</label>
                <input type="text" id="etablissementOrigine" name="etablissementOrigine">
            </div>
            
            <div class="form-group">
                <label for="laboratoireOrigine">Laboratoire d'Origine :</label>
                <input type="text" id="laboratoireOrigine" name="laboratoireOrigine">
            </div>
            
            <button type="submit">Enregistrer le membre</button>
        </form>
    </div>

</body>
</html>