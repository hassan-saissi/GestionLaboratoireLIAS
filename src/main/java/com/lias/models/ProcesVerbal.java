package com.lias.models;

import java.util.Date;

public class ProcesVerbal {
    private Long id;
    private String titreReunion;
    private String ordreDuJour;
    private Date dateReunion;
    private Long evenementId;

    public ProcesVerbal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitreReunion() { return titreReunion; }
    public void setTitreReunion(String titreReunion) { this.titreReunion = titreReunion; }

    public String getOrdreDuJour() { return ordreDuJour; }
    public void setOrdreDuJour(String ordreDuJour) { this.ordreDuJour = ordreDuJour; }

    public Date getDateReunion() { return dateReunion; }
    public void setDateReunion(Date dateReunion) { this.dateReunion = dateReunion; }

    public Long getEvenementId() { return evenementId; }
    public void setEvenementId(Long evenementId) { this.evenementId = evenementId; }
}