package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.PlatoNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.ActualizarPlatoPort;

import java.util.List;
import java.util.Objects;

public class ActualizarPlatoAdapter implements ActualizarPlatoPort {
    private final List<Plato> platos = PlatosMemoria.obtenerPlatos();

    @Override
    public void actualizar(Plato plato) {
        for (int i = 0; i < platos.size(); i++) {
            if (Objects.equals(platos.get(i).getId(), plato.getId())) {
                platos.set(i, plato);
                return;
            }
        }
        throw new PlatoNoExisteException(plato.getId());
    }
}