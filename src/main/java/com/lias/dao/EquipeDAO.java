package com.lias.dao;

import com.lias.models.Equipe;
import com.lias.models.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public List<Equipe> getAllEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipe e = new Equipe();
                e.setId(rs.getLong("id"));
                e.setNomEquipe(rs.getString("nomEquipe"));
                e.setDescription(rs.getString("description"));
                equipes.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }

    public Equipe getEquipeById(Long id) {
        String sql = "SELECT * FROM equipe WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Equipe e = new Equipe();
                    e.setId(rs.getLong("id"));
                    e.setNomEquipe(rs.getString("nomEquipe"));
                    e.setDescription(rs.getString("description"));
                    return e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean ajouterEquipe(Equipe equipe) {
        String sql = "INSERT INTO equipe (nomEquipe, description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipe.getNomEquipe());
            pstmt.setString(2, equipe.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEquipe(Long id) {
        String sql = "DELETE FROM equipe WHERE id = ?";
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
     * Récupère la liste des membres actifs d'une équipe (dateFin IS NULL dans historique_affiliation)
     */
    public List<Membre> getMembresByEquipe(Long equipeId) {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT m.* FROM membre m " +
                     "JOIN historique_affiliation h ON m.id = h.membre_id " +
                     "WHERE h.equipe_id = ? AND h.dateFin IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, equipeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Membre m = new Membre();
                    m.setId(rs.getLong("id"));
                    m.setNom(rs.getString("nom"));
                    m.setPrenom(rs.getString("prenom"));
                    m.setEmail(rs.getString("email"));
                    m.setStatut(rs.getString("statut"));
                    membres.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    /**
     * Récupère la liste de toutes les équipes auxquelles un membre est actuellement affecté
     */
    public List<Equipe> getEquipesByMembre(Long membreId) {
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
                    eq.setDescription(rs.getString("description"));
                    equipes.add(eq);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }


}