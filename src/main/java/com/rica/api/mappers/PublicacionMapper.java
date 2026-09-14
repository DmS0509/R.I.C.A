package com.rica.api.mappers;

import com.rica.api.models.Publicacion;
import com.rica.api.request.PublicacionRequest;
import com.rica.api.response.PublicacionResponse;

public class PublicacionMapper {

    private PublicacionMapper() {
    }

    public static Publicacion aEntidad(PublicacionRequest request) {
        Publicacion publicacion = new Publicacion();
        publicacion.setInvestigadorCorreo(request.getInvestigadorCorreo());
        publicacion.setTitulo(request.getTitulo());
        publicacion.setTipo(request.getTipo());
        publicacion.setAnio(request.getAnio());
        publicacion.setDetalles(request.getDetalles());
        return publicacion;
    }

    public static PublicacionResponse aResponse(Publicacion publicacion) {
        return new PublicacionResponse(
                publicacion.getId(),
                publicacion.getInvestigadorCorreo(),
                publicacion.getTitulo(),
                publicacion.getTipo(),
                publicacion.getAnio(),
                publicacion.getDetalles()
        );
    }
}
