package com.rica.api.publicaciones.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rica.api.compartido.RecursoNoEncontradoException;
import com.rica.api.publicaciones.dominio.Publicacion;


@Service 
public class PublicacionService implements PublicacionUseCase{

    private final RepositorioPublicaciones repositorioPublicaciones;
    private final VerificadorInvestigador verificadorInvestigador;

    public PublicacionService(RepositorioPublicaciones repositorioPublicaciones, VerificadorInvestigador verificadorInvestigador) {
        this.repositorioPublicaciones = repositorioPublicaciones;
        this.verificadorInvestigador = verificadorInvestigador;
    }

    @Override 
    public Publicacion registrar(Publicacion publicacion) {
         if (!verificadorInvestigador.existe(publicacion.getInvestigadorCorreo())) {
            throw new RecursoNoEncontradoException(
                    "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        }
        return repositorioPublicaciones.guardar(publicacion);
    }

    @Override 
   public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return repositorioPublicaciones.listarPorInvestigador(investigadorCorreo);
    }

    @Override 
    public Publicacion buscarPorId(String id) {
        return repositorioPublicaciones.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró la publicación con id: " + id));
    }
}
