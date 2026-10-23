package com.lias.models;

import java.util.Date;

public class Partenaire {
    private Long id;
    private String nomPartenaire;
    private String objetConvention;
    private Date dateDebut;
    private Date dateFin;
    private String fichierConventionURL;
    private Long evenementId;

    public Partenaire() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomPartenaire() { return nomPartenaire; }
    public void setNomPartenaire(String nomPartenaire) { this.nomPartenaire = nomPartenaire; }

    public String getObjetConvention() { return objetConvention; }
    public void setObjetConvention(String objetConvention) { this.objetConvention = objetConvention; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public String getFichierConventionURL() { return fichierConventionURL; }
    public void setFichierConventionURL(String fichierConventionURL) { this.fichierConventionURL = fichierConventionURL; }

    public Long getEvenementId() { return evenementId; }
    public void setEvenementId(Long evenementId) { this.evenementId = evenementId; }
}