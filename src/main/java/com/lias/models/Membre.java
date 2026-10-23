package com.lias.models;

import java.util.Date;

public class Membre {
    private Long id;
    private String nom;
    private String prenom;
    private String photoURL;
    private String email;
    private String password;
    private String biography;
    private String centresInteret;
    private String etablissementOrigine;
    private String laboratoireOrigine;
    private Date dateNaissance;
    private Date dateEmbauche;
    private Date dateAffiliation;
    private boolean compteActif;
    private String statut; // permanent, associé, doctorant, retraité, etc.

    public Membre() {}

    public Membre(Long id, String nom, String prenom, String email, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statut = statut;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPhotoURL() { return photoURL; }
    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getCentresInteret() { return centresInteret; }
    public void setCentresInteret(String centresInteret) { this.centresInteret = centresInteret; }

    public String getEtablissementOrigine() { return etablissementOrigine; }
    public void setEtablissementOrigine(String etablissementOrigine) { this.etablissementOrigine = etablissementOrigine; }

    public String getLaboratoireOrigine() { return laboratoireOrigine; }
    public void setLaboratoireOrigine(String laboratoireOrigine) { this.laboratoireOrigine = laboratoireOrigine; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public Date getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(Date dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public Date getDateAffiliation() { return dateAffiliation; }
    public void setDateAffiliation(Date dateAffiliation) { this.dateAffiliation = dateAffiliation; }

    public boolean isCompteActif() { return compteActif; }
    public void setCompteActif(boolean compteActif) { this.compteActif = compteActif; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}