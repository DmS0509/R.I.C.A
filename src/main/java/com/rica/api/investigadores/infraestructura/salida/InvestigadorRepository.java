package com.rica.api.investigadores.infraestructura.salida;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rica.api.investigadores.dominio.Investigador;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String valor);
   
}
