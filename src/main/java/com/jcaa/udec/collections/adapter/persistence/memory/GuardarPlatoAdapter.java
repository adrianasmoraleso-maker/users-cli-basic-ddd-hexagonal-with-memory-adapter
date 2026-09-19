package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.PlatoYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.GuardarPlatoPort;

import java.util.List;
import java.util.Objects;

public class GuardarPlatoAdapter implements GuardarPlatoPort {
    private final List<Plato> platos = PlatosMemoria.obtenerPlatos();

    @Override
    public void guardar(Plato plato) {
        boolean existe = platos.stream()
                .anyMatch(p -> Objects.equals(p.getId(), plato.getId()));
        if (existe) {
            throw new PlatoYaExisteException(plato.getId());
        }
        platos.add(plato);
    }
}