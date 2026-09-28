package com.rica.api.investigadores.infraestructura.entrada.web;

import com.rica.api.investigadores.dominio.CorreoInstitucional;
import com.rica.api.investigadores.dominio.Investigador;

public class InvestigadorMapper {

    private InvestigadorMapper() {
    }

    public static Investigador aEntidad(InvestigadorRequest request) {
        Investigador investigador = new Investigador();
        investigador.setNombreCompleto(request.getNombreCompleto());
        investigador.setCorreoInstitucional(new CorreoInstitucional(request.getCorreoInstitucional()));
        investigador.setGrupoDeInvestigacion(request.getGrupoInvestigacion());
        return investigador;
    }

    public static InvestigadorResponse aResponse(Investigador investigador) {
        return new InvestigadorResponse(
                investigador.getId(),
                investigador.getNombreCompleto(),
                investigador.getGrupoDeInvestigacion()
        );
    }
}
