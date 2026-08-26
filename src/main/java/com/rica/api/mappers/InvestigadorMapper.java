package com.rica.api.mappers;

import com.rica.api.models.Investigador;
import com.rica.api.request.InvestigadorRequest;
import com.rica.api.response.InvestigadorResponse;

public class InvestigadorMapper {

    private InvestigadorMapper() {
    }

    public static Investigador aEntidad(InvestigadorRequest request) {
        Investigador investigador = new Investigador();
        investigador.setNombreCompleto(request.getNombreCompleto());
        investigador.setCorreoInstitucional(request.getCorreoInstitucional());
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
