package com.lias.models;

import java.util.Date;

public class Equipe {
    private Long id;
    private String nomEquipe;
    private String description;
    private Date dateCreation;

    public Equipe() {}

    public Equipe(Long id, String nomEquipe, String description, Date dateCreation) {
        this.id = id;
        this.nomEquipe = nomEquipe;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

}