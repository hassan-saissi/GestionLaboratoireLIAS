package com.lias.dao;

import com.lias.models.DemandeMateriel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeMaterielDAO {

    public boolean ajouterDemande(DemandeMateriel demande) {
        String sql = "INSERT INTO demande_materiel (descriptionBesoin, quantite, dateDemande, statutDemande, membre_id) VALUES (?, ?, NOW(), 'EN_ATTENTE', ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, demande.getDescriptionBesoin());
            pstmt.setInt(2, demande.getQuantite());
            pstmt.setLong(3, demande.getMembreId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DemandeMateriel> getDemandesByMembre(Long membreId) {
        List<DemandeMateriel> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande_materiel WHERE membre_id = ? ORDER BY id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, membreId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DemandeMateriel d = new DemandeMateriel();
                    d.setId(rs.getLong("id"));
                    d.setDescriptionBesoin(rs.getString("descriptionBesoin"));
                    d.setQuantite(rs.getInt("quantite"));
                    d.setDateDemande(rs.getTimestamp("dateDemande"));
                    d.setStatutDemande(rs.getString("statutDemande"));
                    d.setMembreId(rs.getLong("membre_id"));
                    demandes.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
}