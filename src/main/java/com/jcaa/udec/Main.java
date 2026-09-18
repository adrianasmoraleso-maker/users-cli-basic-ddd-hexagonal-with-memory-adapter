package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.*;
import com.jcaa.udec.collections.application.service.*;
import com.jcaa.udec.collections.application.service.ports.in.*;
import com.jcaa.udec.collections.domain.port.out.*;
import com.jcaa.udec.collections.entrypoint.cli.GuiCli;
import com.jcaa.udec.collections.entrypoint.controller.*;

public class Main {
    public static void main(String[] args) {
        // Usuario (sin cambios)
        GuardarUsuarioPort guardarUsuarioPort = new GuardarUsuarioAdapter();
        ObtenerUsuariosPort obtenerUsuariosPort = new ObtenerUsuariosAdapter();
        AgregarUsuarioUseCase agregarUsuarioUseCase = new AgregarUsuarioService(guardarUsuarioPort);
        ObtenerUsuarioUseCase obtenerUsuarioUseCase = new ObtenerUsuariosService(obtenerUsuariosPort);
        UsuarioControlador usuarioControlador =
                new UsuarioControladorImpl(agregarUsuarioUseCase, obtenerUsuarioUseCase);

        // Plato (nuevo)
        GuardarPlatoPort guardarPlatoPort = new GuardarPlatoAdapter();
        ObtenerPlatosPort obtenerPlatosPort = new ObtenerPlatosAdapter();
        ActualizarPlatoPort actualizarPlatoPort = new ActualizarPlatoAdapter();
        EliminarPlatoPort eliminarPlatoPort = new EliminarPlatoAdapter();

        AgregarPlatoUseCase agregarPlatoUseCase = new AgregarPlatoService(guardarPlatoPort);
        BuscarPlatoUseCase buscarPlatoUseCase = new BuscarPlatoService(obtenerPlatosPort);
        ActualizarPlatoUseCase actualizarPlatoUseCase = new ActualizarPlatoService(actualizarPlatoPort);
        EliminarPlatoUseCase eliminarPlatoUseCase = new EliminarPlatoService(eliminarPlatoPort);

        PlatoControlador platoControlador = new PlatoControladorImpl(
                agregarPlatoUseCase, buscarPlatoUseCase, actualizarPlatoUseCase, eliminarPlatoUseCase);

        GuiCli guiCli = new GuiCli(usuarioControlador, platoControlador);
        guiCli.ejecutarAccion();
    }
}