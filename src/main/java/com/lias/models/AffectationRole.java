package com.lias.models;

import java.util.Date;

public class AffectationRole {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private boolean estActif;
    private String role;
    private Long membreId;

    public AffectationRole() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public boolean isEstActif() { return estActif; }
    public void setEstActif(boolean estActif) { this.estActif = estActif; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }
}