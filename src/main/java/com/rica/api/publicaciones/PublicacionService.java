package com.rica.api.publicaciones;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.rica.api.compartido.RecursoNoEncontradoException;
import com.rica.api.investigadores.InvestigadorRepository;

@Service 
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, InvestigadorRepository investigadorRepository) {
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
    }

    public Publicacion registrar(Publicacion publicacion) {
        boolean investigadorExiste = investigadorRepository.findAll().stream()
            .anyMatch(investigador -> Objects.equals(
                String.valueOf(investigador.getCorreoInstitucional()),
                publicacion.getInvestigadorCorreo()));
        if (!investigadorExiste) {
            throw new RecursoNoEncontradoException("No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        }
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(String id){
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la publicación con id: " + id));
    }
}
