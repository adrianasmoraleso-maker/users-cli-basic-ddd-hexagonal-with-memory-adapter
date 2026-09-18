package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.mapper.PlatoMapper;
import com.jcaa.udec.collections.application.service.dto.command.CrearPlatoComando;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarPlatoUseCase;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.ActualizarPlatoPort;

public class ActualizarPlatoService implements ActualizarPlatoUseCase {
    private final ActualizarPlatoPort actualizarPlatoPort;

    public ActualizarPlatoService(ActualizarPlatoPort actualizarPlatoPort) {
        this.actualizarPlatoPort = actualizarPlatoPort;
    }

    @Override
    public void actualizar(CrearPlatoComando comando) {
        Plato plato = PlatoMapper.mapearAPlato(comando);
        actualizarPlatoPort.actualizar(plato);
    }
}