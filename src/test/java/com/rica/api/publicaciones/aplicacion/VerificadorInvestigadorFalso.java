package com.rica.api.publicaciones.aplicacion;

import java.util.Set;

class VerificadorInvestigadorFalso implements VerificadorInvestigador{

    private final Set<String> correosConocidos;
 
    VerificadorInvestigadorFalso(String... correosConocidos) {
        this.correosConocidos = Set.of(correosConocidos);
    }
 
    @Override
    public boolean existe(String correoInstitucional) {
        return correosConocidos.contains(correoInstitucional);
    }
}
