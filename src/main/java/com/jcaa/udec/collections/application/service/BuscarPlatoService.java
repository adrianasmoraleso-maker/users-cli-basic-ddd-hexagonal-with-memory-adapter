package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.ports.in.BuscarPlatoUseCase;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.domain.port.out.ObtenerPlatosPort;

import java.util.List;

public class BuscarPlatoService implements BuscarPlatoUseCase {
    private final ObtenerPlatosPort obtenerPlatosPort;

    public BuscarPlatoService(ObtenerPlatosPort obtenerPlatosPort) {
        this.obtenerPlatosPort = obtenerPlatosPort;
    }

    @Override
    public Plato buscarPorId(String id) {
        return obtenerPlatosPort.buscarPorId(id);
    }

    @Override
    public List<Plato> obtenerTodos() {
        return obtenerPlatosPort.obtenerTodos();
    }
}