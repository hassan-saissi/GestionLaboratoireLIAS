package com.lias.models;

import java.util.Date;

public class Materiel {
    private Long id;
    private String codeInventaire;
    private String typeMaternel;
    private String designation;
    private Date dateArrivage;
    private String etat;

    public Materiel() {}

    public Materiel(Long id, String codeInventaire, String typeMaternel, String designation, Date dateArrivage, String etat) {
        this.id = id;
        this.codeInventaire = codeInventaire;
        this.typeMaternel = typeMaternel;
        this.designation = designation;
        this.dateArrivage = dateArrivage;
        this.etat = etat;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeInventaire() { return codeInventaire; }
    public void setCodeInventaire(String codeInventaire) { this.codeInventaire = codeInventaire; }

    public String getTypeMaternel() { return typeMaternel; }
    public void setTypeMaternel(String typeMaternel) { this.typeMaternel = typeMaternel; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Date getDateArrivage() { return dateArrivage; }
    public void setDateArrivage(Date dateArrivage) { this.dateArrivage = dateArrivage; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
}