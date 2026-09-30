package com.rica.api.publicaciones.aplicacion;

import org.springframework.stereotype.Component;
 
import com.rica.api.investigadores.aplicacion.RepositorioInvestigadores;

@Component
public class VerificadorInvestigadorLocalAdapter implements VerificadorInvestigador{

    private final RepositorioInvestigadores repositorioInvestigadores;
 
    public VerificadorInvestigadorLocalAdapter(RepositorioInvestigadores repositorioInvestigadores) {
        this.repositorioInvestigadores = repositorioInvestigadores;
    }
 
    @Override
    public boolean existe(String correoInstitucional) {
        return repositorioInvestigadores.existeCorreoInstitucional(correoInstitucional);
    }
}
