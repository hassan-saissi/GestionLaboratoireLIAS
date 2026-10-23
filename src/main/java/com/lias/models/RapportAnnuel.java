package com.lias.models;

import java.util.Date;

public class RapportAnnuel {
    private Long id;
    private int annee;
    private Date dateGeneration;

    public RapportAnnuel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public Date getDateGeneration() { return dateGeneration; }
    public void setDateGeneration(Date dateGeneration) { this.dateGeneration = dateGeneration; }
}