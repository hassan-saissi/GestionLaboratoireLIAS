<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accès Refusé - Laboratoire LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8d7da; text-align: center; padding-top: 100px; }
        .error-card { background: white; max-width: 500px; margin: auto; padding: 40px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h1 { color: #721c24; margin-bottom: 10px; }
        p { color: #333; margin-bottom: 20px; }
        a { background-color: #004085; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; }
    </style>
</head>
<body>

    <div class="error-card">
        <h1>403 - Accès Refusé</h1>
        <p>Vous n'avez pas les droits nécessaires pour accéder à cette zone restreinte.</p>
        <a href="${pageContext.request.contextPath}/login">Retour à l'accueil</a>
    </div>

</body>
</html>