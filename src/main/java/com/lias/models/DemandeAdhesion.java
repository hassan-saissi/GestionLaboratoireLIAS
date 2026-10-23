package com.lias.models;

import java.sql.Timestamp;
import java.util.Date;


public class DemandeAdhesion {
	private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String cvURL;  
    private String etablissement; // Added field
    private String lettreMotivation;
    private String statutDemande;
    private Date dateSoumission;
    private Date dateDecision;
    private Long traiteParMembreId;      

    // Constructeur par défaut
    public DemandeAdhesion() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public String getStatutDemande() {
        return statutDemande;
    }

    public void setStatutDemande(String statutDemande) {
        this.statutDemande = statutDemande;
    }
    public String getCvURL() {
        return cvURL;
    }

    public void setCvURL(String cvURL) {
        this.cvURL = cvURL;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Date getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(Date dateDecision) {
        this.dateDecision = dateDecision;
    }

    public Long getTraiteParMembreId() {
        return traiteParMembreId;
    }

    public void setTraiteParMembreId(Long traiteParMembreId) {
        this.traiteParMembreId = traiteParMembreId;
    }

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
}