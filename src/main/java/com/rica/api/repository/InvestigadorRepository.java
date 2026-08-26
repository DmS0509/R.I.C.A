package com.rica.api.repository;

import java.util.List;
import java.util.Optional;

import com.rica.api.models.Investigador;

public interface InvestigadorRepository {

    List<Investigador> findAll();

    Optional<Investigador> findById(Long id);

    Investigador save(Investigador investigador);

    boolean existsByCorreoInstitucional(String correoInstitucional);
}
