package com.rica.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rica.api.models.Publicacion;

public interface PublicacionRepository extends MongoRepository<Publicacion, String> {

    List<Publicacion> findByInvestigadorCorreo(String investigadorCorreo);

}
