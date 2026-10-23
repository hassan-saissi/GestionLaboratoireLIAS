package com.lias.models;

public class Publication {
    private Long id;
    private String titre;
    private String resume;
    private int annee;
    private String typePublication;
    private Long auteurMembreId;
    private Long equipeId;

    public Publication() {}

    public Publication(Long id, String titre, String resume, int annee, String typePublication, Long auteurMembreId, Long equipeId) {
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.typePublication = typePublication;
        this.auteurMembreId = auteurMembreId;
        this.equipeId = equipeId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public String getTypePublication() { return typePublication; }
    public void setTypePublication(String typePublication) { this.typePublication = typePublication; }

    public Long getAuteurMembreId() { return auteurMembreId; }
    public void setAuteurMembreId(Long auteurMembreId) { this.auteurMembreId = auteurMembreId; }

    public Long getEquipeId() { return equipeId; }
    public void setEquipeId(Long equipeId) { this.equipeId = equipeId; }
}