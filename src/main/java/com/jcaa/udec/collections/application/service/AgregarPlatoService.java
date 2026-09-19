package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.mapper.PlatoMapper;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.application.service.dto.command.CrearPlatoComando;
import com.jcaa.udec.collections.application.service.ports.in.AgregarPlatoUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarPlatoPort;

public class AgregarPlatoService implements AgregarPlatoUseCase {
    private final GuardarPlatoPort guardarPlatoPort;

    public AgregarPlatoService(GuardarPlatoPort guardarPlatoPort) {
        this.guardarPlatoPort = guardarPlatoPort;
    }

    @Override
    public void guardar(CrearPlatoComando comando) {
        Plato plato = PlatoMapper.mapearAPlato(comando);
        guardarPlatoPort.guardar(plato);
    }
}