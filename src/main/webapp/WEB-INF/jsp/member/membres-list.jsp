<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LIAS Laboratory - Members List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: #fff; margin-top: 20px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
    </style>
</head>
<body>

    <h2>Liste des Membres du Laboratoire LIAS</h2>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Statut</th>
            <th>Établissement d'Origine</th>
        </tr>
        <c:forEach var="m" items="${membres}">
            <tr>
                <td>${m.id}</td>
                <td>${m.nom}</td>
                <td>${m.prenom}</td>
                <td>${m.email}</td>
                <td>${m.statut}</td>
                <td>${m.etablissementOrigine}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>