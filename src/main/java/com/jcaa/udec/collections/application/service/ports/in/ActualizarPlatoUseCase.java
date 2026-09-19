package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearPlatoComando;

public interface ActualizarPlatoUseCase {
    void actualizar(CrearPlatoComando comando);
}