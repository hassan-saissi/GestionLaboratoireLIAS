package com.lias.models;

import java.util.Date;

public class HistoriqueAffiliation {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private Long membreId;
    private Long equipeId;

    public HistoriqueAffiliation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }

    public Long getEquipeId() { return equipeId; }
    public void setEquipeId(Long equipeId) { this.equipeId = equipeId; }
}