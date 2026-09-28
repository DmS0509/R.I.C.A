package com.rica.api.investigadores.aplicacion;

import java.util.List;
import java.util.Optional;

import com.rica.api.investigadores.dominio.Investigador;

public interface RepositorioInvestigadores {

    List<Investigador> listarTodos();

    Optional<Investigador> buscarPorId(Long id);

    boolean existeCorreoInstitucional(String correoInstitucional);
    
    Investigador guardar(Investigador investigador);

}
