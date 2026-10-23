package com.lias.models;

import java.util.Date;

public class OrganisateurEvenement {
    private Long id;
    private String roleOrganisation;
    private Date dateAffectation;
    private Long membreId;
    private Long evenementId;

    public OrganisateurEvenement() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleOrganisation() { return roleOrganisation; }
    public void setRoleOrganisation(String roleOrganisation) { this.roleOrganisation = roleOrganisation; }

    public Date getDateAffectation() { return dateAffectation; }
    public void setDateAffectation(Date dateAffectation) { this.dateAffectation = dateAffectation; }

    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }

    public Long getEvenementId() { return evenementId; }
    public void setEvenementId(Long evenementId) { this.evenementId = evenementId; }
}