package com.rica.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rica.api.exceptions.CorreoDuplicadoException;
import com.rica.api.exceptions.RecursoNoEncontradoException;
import com.rica.api.models.Investigador;
import com.rica.api.repository.InvestigadorRepository;

@Service
public class InvestigadorService {

    private final InvestigadorRepository investigadorRepository;

    public InvestigadorService(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.findAll();
    }

    public Investigador buscarPorId(Long id) {
        return investigadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                "No existe un investigador con id " + id));
    }

    public Investigador registrar(Investigador investigador) {
        if (investigadorRepository.existsByCorreoInstitucional(investigador.getCorreoInstitucional())) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + investigador.getCorreoInstitucional());
        }
        return investigadorRepository.save(investigador);
    }

}
