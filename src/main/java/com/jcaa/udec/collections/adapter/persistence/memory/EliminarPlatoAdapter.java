package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.PlatoNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.EliminarPlatoPort;

import java.util.List;
import java.util.Objects;

public class EliminarPlatoAdapter implements EliminarPlatoPort {
    private final List<Plato> platos = PlatosMemoria.obtenerPlatos();

    @Override
    public void eliminar(String id) {
        boolean eliminado = platos.removeIf(p -> Objects.equals(p.getId(), id));
        if (!eliminado) {
            throw new PlatoNoExisteException(id);
        }
    }
}