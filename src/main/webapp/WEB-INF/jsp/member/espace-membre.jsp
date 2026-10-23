<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon Espace Membre - LIAS</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 30px; }
        .container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h2, h3 { color: #004085; }
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-top: 20px; }
        .card { background: #fdfdfd; border: 1px solid #e3e3e3; padding: 20px; border-radius: 6px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { padding: 8px; border: 1px solid #ddd; text-align: left; font-size: 14px; }
        th { background-color: #004085; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 4px; font-size: 13px; }
        .form-group input, .form-group textarea, .form-group select { width: 100%; padding: 6px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #28a745; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }
        .btn-submit:hover { background-color: #218838; }
        .badge { background: #17a2b8; color: white; padding: 3px 8px; border-radius: 4px; font-size: 12px; display: inline-block; }
    </style>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />
    
    <div class="container">

        <!-- Messages de succès -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div style="background-color: #d4edda; color: #155724; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
                ${sessionScope.successMessage}
            </div>
            <% session.removeAttribute("successMessage"); %>
        </c:if>

        <h2>Mon Espace Personnel - ${userSession.prenom} ${userSession.nom}</h2>

        <div class="grid">
            
            <!-- SECTION 1 : MES ÉQUIPES ET COLLÈGUES -->
            <div class="card">
                <h3>Mes Équipes & Collègues</h3>
                <c:forEach var="equip" items="${mesEquipes}">
                    <div style="margin-bottom: 15px; border-bottom: 1px solid #eee; padding-bottom: 10px;">
                        <strong>Équipe : ${equip.nomEquipe}</strong>
                        <p style="margin: 5px 0; font-size: 13px; color: #666;">${equip.description}</p>
                        
                        <p style="font-size: 13px; font-weight: bold; margin-top: 8px;">Membres de cette équipe :</p>
                        <ul>
                            <c:forEach var="collegue" items="${colleguesParEquipe[equip]}">
                                <li>
                                    ${collegue.prenom} ${collegue.nom} 
                                    <c:if test="${collegue.id != userSession.id}">
                                        <span class="badge">${collegue.statut}</span>
                                    </c:if>
                                    <c:if test="${collegue.id == userSession.id}">
                                        <span class="badge" style="background-color: #28a745;">Moi</span>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
                <c:if test="${empty mesEquipes}">
                    <p style="color: #666;">Vous n'êtes affecté à aucune équipe pour le moment.</p>
                </c:if>
            </div>

            <!-- SECTION 2 : DEMANDES DE MATÉRIEL -->
            <div class="card">
                <h3>Demander du Matériel</h3>
                <form action="${pageContext.request.contextPath}/member/materiel/add" method="post">
                    <div class="form-group">
                        <label>Description du besoin :</label>
                        <textarea name="descriptionBesoin" rows="2" required placeholder="Détails du matériel nécessaire..."></textarea>
                    </div>
                    <div class="form-group">
                        <label>Quantité :</label>
                        <input type="number" name="quantite" required value="1" min="1">
                    </div>
                    <button type="submit" class="btn-submit">Envoyer la demande</button>
                </form>

                <h4 style="margin-top: 20px;">Historique de mes demandes</h4>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Qté</th>
                        <th>Statut</th>
                    </tr>
                    <c:forEach var="d" items="${mesDemandes}">
                        <tr>
                            <td>${d.dateDemande}</td>
                            <td>${d.descriptionBesoin}</td>
                            <td>${d.quantite}</td>
                            <td>
                                <span class="badge" style="background-color: ${d.statutDemande == 'EN_ATTENTE' ? '#ffc107' : '#28a745'}; color: ${d.statutDemande == 'EN_ATTENTE' ? '#000' : '#fff'};">
                                    ${d.statutDemande}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty mesDemandes}">
                        <tr><td colspan="4" style="text-align: center; color: #666;">Aucune demande de matériel.</td></tr>
                    </c:if>
                </table>
            </div>

        </div>

        <!-- SECTION 3 : PUBLICATIONS SCIENTIFIQUES -->
        <div class="card" style="margin-top: 20px;">
            <h3>Mes Publications Scientifiques</h3>
            <form action="${pageContext.request.contextPath}/member/publication/add" method="post" style="margin-bottom: 20px;">
                <div style="display: flex; gap: 15px; margin-bottom: 12px;">
                    <div class="form-group" style="flex: 2; margin-bottom: 0;">
                        <label>Titre de la publication :</label>
                        <input type="text" name="titre" required placeholder="Titre de l'article ou de la conférence...">
                    </div>
                    <div class="form-group" style="flex: 1; margin-bottom: 0;">
                        <label>Type :</label>
                        <select name="typePublication">
                            <option value="Article de revue">Article de revue</option>
                            <option value="Conférence">Conférence</option>
                            <option value="Chapitre d'ouvrage">Chapitre d'ouvrage</option>
                            <option value="Thèse">Thèse</option>
                        </select>
                    </div>
                    <div class="form-group" style="flex: 1; margin-bottom: 0;">
                        <label>Année :</label>
                        <input type="number" name="annee" required value="2026">
                    </div>
                </div>

                <div style="display: flex; gap: 15px; align-items: flex-end;">
                    <div class="form-group" style="flex: 2; margin-bottom: 0;">
                        <label>Résumé / Abstract :</label>
                        <input type="text" name="resume" placeholder="Résumé court...">
                    </div>
                    <div class="form-group" style="flex: 1; margin-bottom: 0;">
                        <label>Équipe associée :</label>
                        <select name="equipeId">
                            <option value="">-- Aucune / Indépendante --</option>
                            <c:forEach var="equip" items="${mesEquipes}">
                                <option value="${equip.id}">${equip.nomEquipe}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn-submit" style="height: 34px;">Ajouter</button>
                </div>
            </form>

            <table>
                <tr>
                    <th>Année</th>
                    <th>Type</th>
                    <th>Titre</th>
                    <th>Résumé</th>
                </tr>
                <c:forEach var="p" items="${mesPublications}">
                    <tr>
                        <td><strong>${p.annee}</strong></td>
                        <td><span class="badge">${p.typePublication}</span></td>
                        <td>${p.titre}</td>
                        <td>${p.resume}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty mesPublications}">
                    <tr><td colspan="4" style="text-align: center; color: #666;">Aucune publication enregistrée.</td></tr>
                </c:if>
            </table>
        </div>

    </div>
</body>
</html>