package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.PlatoNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.ObtenerPlatosPort;

import java.util.List;
import java.util.Objects;

public class ObtenerPlatosAdapter implements ObtenerPlatosPort {
    private final List<Plato> platos = PlatosMemoria.obtenerPlatos();

    @Override
    public List<Plato> obtenerTodos() {
        return List.copyOf(platos);
    }

    @Override
    public Plato buscarPorId(String id) {
        for (Plato plato : platos) {
            if (Objects.equals(plato.getId(), id)) {
                return plato;
            }
        }
        throw new PlatoNoExisteException(id);
    }
}