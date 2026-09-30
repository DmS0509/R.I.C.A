package com.rica.api.publicaciones.aplicacion;

import java.util.List;
import java.util.Optional;

import com.rica.api.publicaciones.dominio.Publicacion;

public interface RepositorioPublicaciones {

    List<Publicacion> listarPorInvestigador(String investigadorCorreo);

    Optional<Publicacion> buscarPorId(String id);

    long contarPorInvestigadorYAnio(String investigadorCorreo, Integer anio);

    Publicacion guardar(Publicacion publicacion);
}
