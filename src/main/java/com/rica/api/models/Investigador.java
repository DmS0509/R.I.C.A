package com.rica.api.models;

public class Investigador {

    private Long id;
    private String nombreCompleto;
    private String correoInstitucional;
    private String grupoDeInvestigacion;

    public Investigador() {
    }

    public Investigador(Long id, String nombreCompleto, String correoInstitucional, String grupoDeInvestigacion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correoInstitucional = correoInstitucional;
        this.grupoDeInvestigacion = grupoDeInvestigacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getGrupoDeInvestigacion() {
        return grupoDeInvestigacion;
    }

    public void setGrupoDeInvestigacion(String grupoDeInvestigacion) {
        this.grupoDeInvestigacion = grupoDeInvestigacion;
    }

}
