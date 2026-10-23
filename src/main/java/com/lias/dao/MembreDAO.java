package com.lias.dao;

import com.lias.models.Equipe;
import com.lias.models.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    /**
     * Retrieve all members from the database (useful for public directory or admin view).
     */
    public List<Membre> getAllMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Membre membre = mapResultSetToMembre(rs);
                membres.add(membre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    /**
     * Find a single member by their unique ID.
     */
    public Membre getMembreById(Long id) {
        Membre membre = null;
        String sql = "SELECT * FROM membre WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    membre = mapResultSetToMembre(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    /**
     * Authenticate a member using email and password.
     */
    public Membre authenticate(String email, String password) {
        Membre membre = null;
        String sql = "SELECT * FROM membre WHERE email = ? AND password = ? AND compteActif = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password); // Note: In production, use password hashing (BCrypt)

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    membre = mapResultSetToMembre(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    /**
     * Helper method to map a ResultSet row to a Membre object.
     */
    private Membre mapResultSetToMembre(ResultSet rs) throws SQLException {
        Membre membre = new Membre();
        membre.setId(rs.getLong("id"));
        membre.setNom(rs.getString("nom"));
        membre.setPrenom(rs.getString("prenom"));
        membre.setPhotoURL(rs.getString("photoURL"));
        membre.setEmail(rs.getString("email"));
        membre.setPassword(rs.getString("password"));
        membre.setBiography(rs.getString("biography"));
        membre.setCentresInteret(rs.getString("centresInteret"));
        membre.setEtablissementOrigine(rs.getString("etablissementOrigine"));
        membre.setLaboratoireOrigine(rs.getString("laboratoireOrigine"));
        membre.setDateNaissance(rs.getDate("dateNaissance"));
        membre.setDateEmbauche(rs.getDate("dateEmbauche"));
        membre.setDateAffiliation(rs.getDate("dateAffiliation"));
        membre.setCompteActif(rs.getBoolean("compteActif"));
        membre.setStatut(rs.getString("statut"));
        return membre;
    }

    /**
     * Insert a new member into the database.
     */
    public boolean insertMembre(Membre m) {
        String sql = "INSERT INTO membre (nom, prenom, email, password, statut, etablissementOrigine, laboratoireOrigine, compteActif) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getNom());
            pstmt.setString(2, m.getPrenom());
            pstmt.setString(3, m.getEmail());
            pstmt.setString(4, m.getPassword()); // Note: Plain text for now; hashing can be added later
            pstmt.setString(5, m.getStatut());
            pstmt.setString(6, m.getEtablissementOrigine());
            pstmt.setString(7, m.getLaboratoireOrigine());
            pstmt.setBoolean(8, m.isCompteActif());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete a member by ID.
     */
    public boolean deleteMembre(Long id) {
        String sql = "DELETE FROM membre WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update an existing member's information.
     */
    public boolean updateMembre(Membre m) {
        String sql = "UPDATE membre SET nom = ?, prenom = ?, email = ?, statut = ?, etablissementOrigine = ?, laboratoireOrigine = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getNom());
            pstmt.setString(2, m.getPrenom());
            pstmt.setString(3, m.getEmail());
            pstmt.setString(4, m.getStatut());
            pstmt.setString(5, m.getEtablissementOrigine());
            pstmt.setString(6, m.getLaboratoireOrigine());
            pstmt.setLong(7, m.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Vérifie si un membre possède un rôle actif dans la table affectation_role.
     */
    public boolean hasRole(Long membreId, String roleName) {
        String sql = "SELECT COUNT(*) FROM affectation_role WHERE membre_id = ? AND role = ? AND (estActif = 1 OR estActif IS NULL)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, membreId);
            pstmt.setString(2, roleName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean assignerEquipe(Long membreId, Long equipeId) {
        // 1. Vérifier si le membre appartient déjà activement à cette équipe
        String checkSql = "SELECT COUNT(*) FROM historique_affiliation WHERE membre_id = ? AND equipe_id = ? AND dateFin IS NULL";
        String insertSql = "INSERT INTO historique_affiliation (dateDebut, dateFin, membre_id, equipe_id) VALUES (CURRENT_DATE(), NULL, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            
            // Vérification de l'existence d'une affiliation active
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setLong(1, membreId);
                checkStmt.setLong(2, equipeId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Le membre est déjà dans cette équipe activement
                        return false; 
                    }
                }
            }

            // Si non présent, on procède à l'insertion
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setLong(1, membreId);
                insertStmt.setLong(2, equipeId);
                return insertStmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2. Met fin à l'affectation d'un membre dans une équipe
     */
    public boolean cloturerAffiliation(Long membreId, Long equipeId) {
        String sql = "UPDATE historique_affiliation SET dateFin = CURRENT_DATE() WHERE membre_id = ? AND equipe_id = ? AND dateFin IS NULL";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, membreId);
            pstmt.setLong(2, equipeId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 3. Récupère les équipes actives d'un membre
     */
    public List<Equipe> getEquipesActivesParMembre(Long membreId) {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT e.* FROM equipe e " +
                     "JOIN historique_affiliation h ON e.id = h.equipe_id " +
                     "WHERE h.membre_id = ? AND h.dateFin IS NULL";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, membreId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Equipe eq = new Equipe();
                    eq.setId(rs.getLong("id"));
                    eq.setNomEquipe(rs.getString("nomEquipe"));
                    equipes.add(eq);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }

    /**
     * Recherche des membres par nom, prénom ou email.
     */
    public List<Membre> rechercherMembres(String keyword) {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String queryPattern = "%" + (keyword != null ? keyword.trim() : "") + "%";
            pstmt.setString(1, queryPattern);
            pstmt.setString(2, queryPattern);
            pstmt.setString(3, queryPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Membre m = new Membre();
                    m.setId(rs.getLong("id"));
                    m.setNom(rs.getString("nom"));
                    m.setPrenom(rs.getString("prenom"));
                    m.setEmail(rs.getString("email"));
                    m.setStatut(rs.getString("statut"));
                    m.setEtablissementOrigine(rs.getString("etablissementOrigine"));
                    membres.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }


   /**
     * Récupère le rôle d'un membre depuis la table affectation_role
     */
    public String getRoleByMembreId(Long membreId) {
        String role = "MEMBRE"; // Valeur par défaut si aucun rôle actif n'est trouvé
        
        // Requête adaptée exactement à votre schéma (colonne 'role' et 'membre_id' dans 'affectation_role')
        String sql = "SELECT role FROM affectation_role WHERE membre_id = ? AND (dateFin IS NULL) ORDER BY id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, membreId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
