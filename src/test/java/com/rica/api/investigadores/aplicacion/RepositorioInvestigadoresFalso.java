package com.rica.api.investigadores.aplicacion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rica.api.investigadores.dominio.Investigador;

class RepositorioInvestigadoresFalso implements RepositorioInvestigadores{

    private final Map<Long, Investigador> almacen = new LinkedHashMap<>();
    private long siguienteId = 1;

    @Override
    public List<Investigador> listarTodos() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public Optional<Investigador> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public boolean existeCorreoInstitucional(String correoInstitucional) {
        return almacen.values().stream()
        .anyMatch(i -> i.getCorreoInstitucional().valor().equals(correoInstitucional));
    }

    @Override
    public Investigador guardar(Investigador investigador) {
        if (investigador.getId() == null) {
            investigador.setId(siguienteId++);
        }
        almacen.put(investigador.getId(), investigador);
        return investigador;
    }

}
