package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarPlatoPeticion;

import java.util.List;

public interface PlatoControlador {
    void registrar(RegistrarPlatoPeticion peticion);

    Plato obtenerPorId(String id);

    List<Plato> obtenerTodos();

    void actualizar(RegistrarPlatoPeticion peticion);

    void eliminar(String id);
}