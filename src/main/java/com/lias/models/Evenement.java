package com.lias.models;

import java.util.Date;

public class Evenement {
    private Long id;
    private String titre;
    private String description;
    private String typeEvenement;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;
    private String edition;

    public Evenement() {}

    public Evenement(Long id, String titre, String description, String typeEvenement, Date dateDebut, Date dateFin, String lieu, String edition) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.edition = edition;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTypeEvenement() { return typeEvenement; }
    public void setTypeEvenement(String typeEvenement) { this.typeEvenement = typeEvenement; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }
}