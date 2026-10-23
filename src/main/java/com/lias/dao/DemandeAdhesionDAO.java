package com.lias.dao;

import com.lias.models.DemandeAdhesion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeAdhesionDAO {

    /**
     * Récupère la liste de toutes les demandes d'adhésion.
     */
	public List<DemandeAdhesion> getAllDemandes() {
	    List<DemandeAdhesion> liste = new ArrayList<>();
	    String sql = "SELECT * FROM demande_adhesion ORDER BY dateSoumission DESC";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            DemandeAdhesion d = new DemandeAdhesion();
	            d.setId(rs.getLong("id"));
	            d.setNom(rs.getString("nom"));
	            d.setPrenom(rs.getString("prenom"));
	            d.setEmail(rs.getString("email"));
	            d.setCvURL(rs.getString("cvURL"));
	            d.setEtablissement(rs.getString("etablissement")); // <-- Récupération de l'établissement
	            d.setLettreMotivation(rs.getString("lettreMotivation"));
	            d.setStatutDemande(rs.getString("statutDemande"));
	            d.setDateSoumission(rs.getDate("dateSoumission"));
	            liste.add(d);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return liste;
	}
	
	/**
     * Récupère toutes les demandes en attente (ou toutes selon votre besoin).
     */
    public List<DemandeAdhesion> getDemandesParStatut(String statut) {
        List<DemandeAdhesion> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande_adhesion WHERE statutDemande = ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, statut);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	DemandeAdhesion d = new DemandeAdhesion();
    	            d.setId(rs.getLong("id"));
    	            d.setNom(rs.getString("nom"));
    	            d.setPrenom(rs.getString("prenom"));
    	            d.setEmail(rs.getString("email"));
    	            d.setCvURL(rs.getString("cvURL"));
    	            d.setEtablissement(rs.getString("etablissement")); // <-- Récupération de l'établissement
    	            d.setLettreMotivation(rs.getString("lettreMotivation"));
    	            d.setStatutDemande(rs.getString("statutDemande"));
    	            d.setDateSoumission(rs.getDate("dateSoumission"));
    	            demandes.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }

	/**
     * Met à jour le statut de la demande et crée automatiquement un compte membre si acceptée.
     */
    public boolean updateStatutDemande(Long id, String nouveauStatut) {
        Connection conn = null;
        PreparedStatement pstmtStmt = null;
        PreparedStatement pstmtMembre = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Gestion de transaction pour s'assurer de la cohérence

            // 1. Mettre à jour le statut de la demande
            String sqlUpdate = "UPDATE demande_adhesion SET statutDemande = ? WHERE id = ?";
            pstmtStmt = conn.prepareStatement(sqlUpdate);
            pstmtStmt.setString(1, nouveauStatut);
            pstmtStmt.setLong(2, id);
            int rowsUpdated = pstmtStmt.executeUpdate();

            if (rowsUpdated > 0 && "Acceptee".equalsIgnoreCase(nouveauStatut)) {
                // 2. Récupérer les informations de la demande acceptée
                String sqlSelect = "SELECT * FROM demande_adhesion WHERE id = ?";
                try (PreparedStatement pstmtSel = conn.prepareStatement(sqlSelect)) {
                    pstmtSel.setLong(1, id);
                    rs = pstmtSel.executeQuery();
                    
                    if (rs.next()) {
                        String nom = rs.getString("nom");
                        String prenom = rs.getString("prenom");
                        String email = rs.getString("email");
                        String etablissement = rs.getString("etablissement");

                        // Générer un mot de passe aléatoire sécurisé
                        String tempPassword = com.lias.utils.PasswordUtils.generateTemporaryPassword();

                        // 3. Insérer le nouveau membre dans la table 'membre'
                        String sqlInsertMembre = "INSERT INTO membre (nom, prenom, email, password, etablissementOrigine, statut, compteActif) VALUES (?, ?, ?, ?, ?, 'DOCTORANT', 1)";
                        try (PreparedStatement pstmtIns = conn.prepareStatement(sqlInsertMembre)) {
                            pstmtIns.setString(1, nom);
                            pstmtIns.setString(2, prenom);
                            pstmtIns.setString(3, email);
                            pstmtIns.setString(4, tempPassword); // Insère le mot de passe généré
                            pstmtIns.setString(5, etablissement);
                            pstmtIns.executeUpdate();
                        }

                        // 4. Envoyer l'e-mail au nouveau membre en arrière-plan (ou de manière synchrone)
                        new Thread(() -> {
                            com.lias.utils.EmailUtility.sendTemporaryPassword(email, prenom + " " + nom, tempPassword);
                        }).start();
                    }
                }
            }

            conn.commit(); // Valider la transaction
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            // Fermeture des ressources
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (pstmtStmt != null) pstmtStmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
    
    /**
     * Insère une nouvelle demande d'adhésion soumise par un visiteur.
     */
    public boolean insererDemande(DemandeAdhesion d) {
        // La colonne dateSoumission n'a plus besoin d'être passée, MySQL gère le DEFAULT (CURRENT_DATE)
        String sql = "INSERT INTO demande_adhesion (nom, prenom, email, cvURL, etablissement, lettreMotivation, statutDemande) VALUES (?, ?, ?, ?, ?, ?, 'EN_ATTENTE')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, d.getNom());
            pstmt.setString(2, d.getPrenom());
            pstmt.setString(3, d.getEmail());
            pstmt.setString(4, d.getCvURL());
            pstmt.setString(5, d.getEtablissement());
            pstmt.setString(6, d.getLettreMotivation());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recherche dans les demandes par nom, prénom ou email.
     */
    public List<DemandeAdhesion> rechercherDemandes(String keyword, String statut) {
        List<DemandeAdhesion> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande_adhesion WHERE statut = ? AND (nom LIKE ? OR prenom LIKE ? OR email LIKE ?) ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String pattern = "%" + (keyword != null ? keyword.trim() : "") + "%";
            pstmt.setString(1, statut);
            pstmt.setString(2, pattern);
            pstmt.setString(3, pattern);
            pstmt.setString(4, pattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	DemandeAdhesion d = new DemandeAdhesion();
    	            d.setId(rs.getLong("id"));
    	            d.setNom(rs.getString("nom"));
    	            d.setPrenom(rs.getString("prenom"));
    	            d.setEmail(rs.getString("email"));
    	            d.setCvURL(rs.getString("cvURL"));
    	            d.setEtablissement(rs.getString("etablissement")); // <-- Récupération de l'établissement
    	            d.setLettreMotivation(rs.getString("lettreMotivation"));
    	            d.setStatutDemande(rs.getString("statutDemande"));
    	            d.setDateSoumission(rs.getDate("dateSoumission"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
   
    
    
}