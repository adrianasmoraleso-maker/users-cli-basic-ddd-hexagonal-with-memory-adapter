package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Plato;

import java.util.List;

public interface ObtenerPlatosPort {
    List<Plato> obtenerTodos();

    Plato buscarPorId(String id);
}