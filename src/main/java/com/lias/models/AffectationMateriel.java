package com.lias.models;

import java.util.Date;

public class AffectationMateriel {
    private Long id;
    private Date dateAttribution;
    private Date dateRetour;
    private Long membreId;
    private Long materielId;

    public AffectationMateriel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDateAttribution() { return dateAttribution; }
    public void setDateAttribution(Date dateAttribution) { this.dateAttribution = dateAttribution; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }

    public Long getMaterielId() { return materielId; }
    public void setMaterielId(Long materielId) { this.materielId = materielId; }
}