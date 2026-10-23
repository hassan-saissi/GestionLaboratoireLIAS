package com.lias.models;

import java.util.Date;

public class Document {
    private Long id;
    private String titre;
    private String cheminFichier;
    private Date dateUpload;
    private String typeDocument;
    private Long deposeParMembreId;

    public Document() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getCheminFichier() { return cheminFichier; }
    public void setCheminFichier(String cheminFichier) { this.cheminFichier = cheminFichier; }

    public Date getDateUpload() { return dateUpload; }
    public void setDateUpload(Date dateUpload) { this.dateUpload = dateUpload; }

    public String getTypeDocument() { return typeDocument; }
    public void setTypeDocument(String typeDocument) { this.typeDocument = typeDocument; }

    public Long getDeposeParMembreId() { return deposeParMembreId; }
    public void setDeposeParMembreId(Long deposeParMembreId) { this.deposeParMembreId = deposeParMembreId; }
}