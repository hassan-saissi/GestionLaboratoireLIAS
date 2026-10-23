package com.lias.models;

import java.util.Date;

public class Notification {
    private Long id;
    private String message;
    private Date dateNotification;
    private String typeNotification;
    private boolean estLue;
    private Long membreId;
    private Long evenementId;

    public Notification() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getDateNotification() { return dateNotification; }
    public void setDateNotification(Date dateNotification) { this.dateNotification = dateNotification; }

    public String getTypeNotification() { return typeNotification; }
    public void setTypeNotification(String typeNotification) { this.typeNotification = typeNotification; }

    public boolean isEstLue() { return estLue; }
    public void setEstLue(boolean estLue) { this.estLue = estLue; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }

    public Long getEvenementId() { return evenementId; }
    public void setEvenementId(Long evenementId) { this.evenementId = evenementId; }
}