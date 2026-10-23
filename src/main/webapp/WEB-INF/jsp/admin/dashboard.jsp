<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord Administrateur - LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; }
        .container { max-width: 1100px; margin: 0 auto; padding: 20px; }
        h1 { color: #333; margin-bottom: 20px; }
        
        /* Dashboard Grid Cards */
        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 20px; }
        .card { background: white; border-radius: 8px; padding: 20px; box-shadow: 0 4px 10px rgba(0,0,0,0.08); text-align: center; }
        .card h3 { color: #004085; margin-top: 0; }
        .card .stat { font-size: 32px; font-weight: bold; color: #28a745; margin: 10px 0; }
        .card-btn { display: inline-block; padding: 10px 18px; background-color: #004085; color: white; text-decoration: none; border-radius: 4px; font-weight: bold; margin-top: 10px; }
        .card-btn:hover { background-color: #002752; }
    </style>
</head>
<body>

    <!-- Inclusion de la barre de navigation réutilisable -->
    <jsp:include page="../common/navbar.jsp" />

    <div class="container">
        <h1>Panneau de Contrôle Administrateur</h1>
        
        <div class="grid">
            <!-- Card 1: User Management -->
            <div class="card">
                <h3>Gestion des Utilisateurs</h3>
                <div class="stat">${totalMembres}</div>
                <p>Membre(s) enregistrés dans la base</p>
                <a href="${pageContext.request.contextPath}/admin/users" class="card-btn">Gérer les membres</a>
            </div>

            <!-- Card 2: Quick Create -->
            <div class="card">
                <h3>Nouveau Membre</h3>
                <p>Ajouter un nouvel utilisateur au laboratoire</p>
                <a href="${pageContext.request.contextPath}/admin/create-user" class="card-btn">+ Créer un compte</a>
            </div>

            <!-- Card 3: System Settings -->
            <div class="card">
                <h3>Paramètres Système</h3>
                <p>Configurer les métadonnées et l'année académique</p>
                <a href="${pageContext.request.contextPath}/admin/settings" class="card-btn">Accéder aux réglages</a>
            </div>
        </div>
    </div>

</body>
</html>