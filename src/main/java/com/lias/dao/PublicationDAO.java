package com.lias.dao;

import com.lias.models.Publication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAO {

    public boolean ajouterPublication(Publication pub) {
        String sql = "INSERT INTO publication (titre, resume, annee, typePublication, auteur_membre_id, equipe_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pub.getTitre());
            pstmt.setString(2, pub.getResume());
            pstmt.setInt(3, pub.getAnnee());
            pstmt.setString(4, pub.getTypePublication());
            pstmt.setLong(5, pub.getAuteurMembreId());
            
            if (pub.getEquipeId() != null) {
                pstmt.setLong(6, pub.getEquipeId());
            } else {
                pstmt.setNull(6, Types.BIGINT);
            }
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Publication> getPublicationsByMembre(Long membreId) {
        List<Publication> pubs = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE auteur_membre_id = ? ORDER BY annee DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, membreId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Publication p = new Publication();
                    p.setId(rs.getLong("id"));
                    p.setTitre(rs.getString("titre"));
                    p.setResume(rs.getString("resume"));
                    p.setAnnee(rs.getInt("annee"));
                    p.setTypePublication(rs.getString("typePublication"));
                    p.setAuteurMembreId(rs.getLong("auteur_membre_id"));
                    p.setEquipeId(rs.getLong("equipe_id"));
                    pubs.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pubs;
    }
    
    public List<Publication> getAllPublications() {
        List<Publication> pubs = new ArrayList<>();
        String sql = "SELECT * FROM publication ORDER BY annee DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getLong("id"));
                p.setTitre(rs.getString("titre"));
                p.setResume(rs.getString("resume"));
                p.setAnnee(rs.getInt("annee"));
                p.setTypePublication(rs.getString("typePublication"));
                p.setAuteurMembreId(rs.getLong("auteur_membre_id"));
                p.setEquipeId(rs.getLong("equipe_id"));
                pubs.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pubs;
    }
}