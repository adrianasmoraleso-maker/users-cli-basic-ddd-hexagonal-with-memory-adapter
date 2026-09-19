package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.domain.core.model.Plato;

public interface BuscarPlatoUseCase {
    Plato buscarPorId(String id);

    java.util.List<Plato> obtenerTodos();
}