<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paramètres du Système - Admin LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 40px; }
        .form-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 600px; margin: auto; }
        h2 { color: #004085; text-align: center; margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input[type="text"], input[type="email"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 12px; background-color: #004085; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #002752; }
        .success { color: #155724; background: #d4edda; border: 1px solid #c3e6cb; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .btn-back { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; }
    </style>
</head>
<body>
	<jsp:include page="../common/navbar.jsp" />
    <div class="form-container">
        <h2>Configuration du Laboratoire</h2>
        
        <c:if test="${not empty successMessage}">
            <div class="success">${successMessage}</div>
        </c:if>
        
        <form action="settings" method="post">
            <div class="form-group">
                <label for="labName">Nom du Laboratoire :</label>
                <input type="text" id="labName" name="labName" value="${not empty labName ? labName : 'Laboratoire LIAS'}" required>
            </div>
            
            <div class="form-group">
                <label for="labEmail">Email de Contact du Labo :</label>
                <input type="email" id="labEmail" name="labEmail" value="${not empty labEmail ? labEmail : 'contact@lias.lab'}" required>
            </div>
            
            <div class="form-group">
                <label for="academicYear">Année Universitaire Active :</label>
                <input type="text" id="academicYear" name="academicYear" value="${not empty academicYear ? academicYear : '2025-2026'}" required>
            </div>
            
            <button type="submit">Enregistrer les configurations</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/admin/users" class="btn-back">← Retour au tableau de bord</a>
    </div>

</body>
</html>