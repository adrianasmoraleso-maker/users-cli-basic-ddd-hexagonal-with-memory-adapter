package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.CrearPlatoComando;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarPlatoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.AgregarPlatoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.BuscarPlatoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarPlatoUseCase;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarPlatoPeticion;

import java.util.List;

public class PlatoControladorImpl implements PlatoControlador {
    private final AgregarPlatoUseCase agregarPlatoUseCase;
    private final BuscarPlatoUseCase buscarPlatoUseCase;
    private final ActualizarPlatoUseCase actualizarPlatoUseCase;
    private final EliminarPlatoUseCase eliminarPlatoUseCase;

    public PlatoControladorImpl(
            AgregarPlatoUseCase agregarPlatoUseCase,
            BuscarPlatoUseCase buscarPlatoUseCase,
            ActualizarPlatoUseCase actualizarPlatoUseCase,
            EliminarPlatoUseCase eliminarPlatoUseCase) {
        this.agregarPlatoUseCase = agregarPlatoUseCase;
        this.buscarPlatoUseCase = buscarPlatoUseCase;
        this.actualizarPlatoUseCase = actualizarPlatoUseCase;
        this.eliminarPlatoUseCase = eliminarPlatoUseCase;
    }

    @Override
    public void registrar(RegistrarPlatoPeticion peticion) {
        agregarPlatoUseCase.guardar(new CrearPlatoComando(peticion.id(), peticion.nombre(), peticion.precio()));
    }

    @Override
    public Plato obtenerPorId(String id) {
        return buscarPlatoUseCase.buscarPorId(id);
    }

    @Override
    public List<Plato> obtenerTodos() {
        return buscarPlatoUseCase.obtenerTodos();
    }

    @Override
    public void actualizar(RegistrarPlatoPeticion peticion) {
        actualizarPlatoUseCase.actualizar(new CrearPlatoComando(peticion.id(), peticion.nombre(), peticion.precio()));
    }

    @Override
    public void eliminar(String id) {
        eliminarPlatoUseCase.eliminar(id);
    }
}