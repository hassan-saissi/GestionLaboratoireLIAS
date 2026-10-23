package com.lias.models;

import java.util.Date;

public class Discussion {
    private Long id;
    private String contenu;
    private Date dateEnvoi;
    private Long expediteurMembreId;

    public Discussion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public Date getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(Date dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public Long getExpediteurMembreId() { return expediteurMembreId; }
    public void setExpediteurMembreId(Long expediteurMembreId) { this.expediteurMembreId = expediteurMembreId; }
}