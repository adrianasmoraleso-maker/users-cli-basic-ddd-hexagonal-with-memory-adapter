package com.jcaa.udec.collections.application.service.mapper;

import com.jcaa.udec.collections.application.service.dto.command.CrearPlatoComando;
import com.jcaa.udec.collections.domain.core.model.Plato;

public class PlatoMapper {
    private PlatoMapper() {}

    public static Plato mapearAPlato(CrearPlatoComando comando) {
        return new Plato(comando.id(), comando.nombre(), comando.precio());
    }
}