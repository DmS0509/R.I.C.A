package com.rica.api.publicaciones.aplicacion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rica.api.publicaciones.dominio.Publicacion;

class RepositorioPublicacionesFalso implements RepositorioPublicaciones {

    private final Map<String, Publicacion> almacen = new LinkedHashMap<>();
    private long siguienteId = 1;

    @Override
    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return almacen.values().stream()
                .filter(p -> p.getInvestigadorCorreo().equals(investigadorCorreo))
                .toList();
    }

    @Override
    public Optional<Publicacion> buscarPorId(String id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public long contarPorInvestigadorYAnio(String investigadorCorreo, Integer anio) {
        return almacen.values().stream()
                .filter(p -> p.getInvestigadorCorreo().equals(investigadorCorreo))
                .filter(p -> p.getAnio().equals(anio))
                .count();
    }

    @Override
    public Publicacion guardar(Publicacion publicacion) {
        if (publicacion.getId() == null) {
            publicacion.setId(String.valueOf(siguienteId++));
        }
        almacen.put(publicacion.getId(), publicacion);
        return publicacion;
    }
}
