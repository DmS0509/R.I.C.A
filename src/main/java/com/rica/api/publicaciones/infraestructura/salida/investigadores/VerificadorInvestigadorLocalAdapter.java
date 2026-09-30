package com.rica.api.publicaciones.infraestructura.salida.investigadores;

import org.springframework.stereotype.Component;

import com.rica.api.investigadores.aplicacion.RepositorioInvestigadores;
import com.rica.api.publicaciones.aplicacion.VerificadorInvestigador;

@Component
public class VerificadorInvestigadorLocalAdapter implements VerificadorInvestigador {

    private final RepositorioInvestigadores repositorioInvestigadores;

    public VerificadorInvestigadorLocalAdapter(RepositorioInvestigadores repositorioInvestigadores) {
        this.repositorioInvestigadores = repositorioInvestigadores;
    }

    @Override
    public boolean existe(String correoInstitucional) {
        return repositorioInvestigadores.existeCorreoInstitucional(correoInstitucional);
    }
}
