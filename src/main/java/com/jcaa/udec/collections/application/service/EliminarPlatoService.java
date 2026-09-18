package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.ports.in.EliminarPlatoUseCase;
import com.jcaa.udec.collections.domain.port.out.EliminarPlatoPort;

public class EliminarPlatoService implements EliminarPlatoUseCase {
    private final EliminarPlatoPort eliminarPlatoPort;

    public EliminarPlatoService(EliminarPlatoPort eliminarPlatoPort) {
        this.eliminarPlatoPort = eliminarPlatoPort;
    }

    @Override
    public void eliminar(String id) {
        eliminarPlatoPort.eliminar(id);
    }
}