package com.rica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rica.api.models.Investigador;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional(String correoInstitucional);
   
}
