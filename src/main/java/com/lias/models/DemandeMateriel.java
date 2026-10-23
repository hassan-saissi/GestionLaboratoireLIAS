package com.lias.models;

import java.util.Date;

public class DemandeMateriel {
    private Long id;
    private String descriptionBesoin;
    private int quantite;
    private Date dateDemande;
    private String statutDemande;
    private Long membreId;

    public DemandeMateriel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescriptionBesoin() { return descriptionBesoin; }
    public void setDescriptionBesoin(String descriptionBesoin) { this.descriptionBesoin = descriptionBesoin; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Date getDateDemande() { return dateDemande; }
    public void setDateDemande(Date dateDemande) { this.dateDemande = dateDemande; }

    public String getStatutDemande() { return statutDemande; }
    public void setStatutDemande(String statutDemande) { this.statutDemande = statutDemande; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }
}