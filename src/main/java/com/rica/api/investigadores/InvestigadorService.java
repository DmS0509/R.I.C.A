package com.rica.api.investigadores;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rica.api.compartido.RecursoNoEncontradoException;

@Service
public class InvestigadorService {

    private final InvestigadorRepository investigadorRepository;
    private final InvestigadorFactory investigadorFactory;

    public InvestigadorService(InvestigadorRepository investigadorRepository,
            InvestigadorFactory investigadorFactory) {
        this.investigadorRepository = investigadorRepository;
        this.investigadorFactory = investigadorFactory;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.findAll();
    }

    public Investigador buscarPorId(Long id) {
        return investigadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                "No existe un investigador con id " + id));
    }

    public Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
        Investigador investigador = investigadorFactory.crear(nombreCompleto, correoInstitucional, grupoInvestigacion);
        return investigadorRepository.save(investigador);
    }
}


