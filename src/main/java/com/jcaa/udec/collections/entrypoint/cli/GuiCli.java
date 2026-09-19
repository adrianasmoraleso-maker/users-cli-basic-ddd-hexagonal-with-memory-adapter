package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioYaExisteException;
import com.jcaa.udec.collections.domain.core.exception.PlatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.PlatoNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.PlatoYaExisteException;
import com.jcaa.udec.collections.domain.core.valueobject.Email;
import com.jcaa.udec.collections.domain.core.valueobject.NombreUsuario;
import com.jcaa.udec.collections.domain.core.valueobject.Password;
import com.jcaa.udec.collections.domain.core.valueobject.UsuarioId;
import com.jcaa.udec.collections.domain.core.model.Plato;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.PlatoControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarPlatoPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;

import java.util.List;
import java.util.Scanner;

public class GuiCli {
    private static final int OPCION_AGREGAR_USUARIO = 1;
    private static final int OPCION_BUSCAR_USUARIO = 2;
    private static final int OPCION_MOSTRAR_TODOS_USUARIOS = 3;
    private static final int OPCION_AGREGAR_PLATO = 4;
    private static final int OPCION_BUSCAR_PLATO = 5;
    private static final int OPCION_LISTAR_PLATOS = 6;
    private static final int OPCION_ACTUALIZAR_PLATO = 7;
    private static final int OPCION_ELIMINAR_PLATO = 8;
    private static final int OPCION_SALIR = 9;

    private static final String TEXTO_TITULO = "** EJEMPLO DE USO DE LISTAS Y HEXAGONAL **";
    private static final String TITULO_REGISTRO_USUARIO = "** INGRESE LOS DATOS DEL NUEVO USUARIO **";
    private static final String TITULO_REGISTRO_PLATO = "** INGRESE LOS DATOS DEL NUEVO PLATO **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String OPCIONES = "Opciones:";
    private static final String TEXTO_SOLICITUD_OPCION = "Ingrese el numero de la opcion: ";
    private static final String SOLICITUD_ID = "ID: ";
    private static final String SOLICITUD_PASSWORD = "PASSWORD: ";
    private static final String SOLICITUD_NOMBRE = "NOMBRE: ";
    private static final String SOLICITUD_EMAIL = "EMAIL: ";
    private static final String SOLICITUD_NOMBRE_PLATO = "NOMBRE DEL PLATO: ";
    private static final String SOLICITUD_PRECIO = "PRECIO: ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida";
    private static final String MENSAJE_ID_INVALIDO = "ID INVALIDO: debe ser un numero entero";
    private static final String MENSAJE_PASSWORD_INVALIDO =
            "PASSWORD INVALIDO: minimo 10 caracteres, con mayuscula, minuscula, numero y simbolo";
    private static final String MENSAJE_NOMBRE_INVALIDO = "NOMBRE INVALIDO: minimo 3 caracteres";
    private static final String MENSAJE_EMAIL_INVALIDO = "EMAIL INVALIDO: ingrese un correo valido";
    private static final String MENSAJE_PRECIO_INVALIDO = "PRECIO INVALIDO: debe ser un numero positivo";
    private static final String MENSAJE_ERROR = "ERROR: ";
    private static final String MENSAJE_REGISTRO_EXITOSO = "Usuario registrado correctamente.";
    private static final String MENSAJE_REGISTRO_PLATO_EXITOSO = "Plato registrado correctamente.";
    private static final String MENSAJE_ACTUALIZACION_PLATO_EXITOSA = "Plato actualizado correctamente.";
    private static final String MENSAJE_ELIMINACION_PLATO_EXITOSA = "Plato eliminado correctamente.";
    private static final String MENSAJE_LISTA_VACIA = "No hay usuarios registrados.";
    private static final String MENSAJE_LISTA_PLATOS_VACIA = "No hay platos registrados.";
    private static final String MENSAJE_DESPEDIDA = "Esperamos tu regreso. Bye, Bye";
    private static final String MARCA_ORDEN_BYTES = "\uFEFF";
    private static final String TEXTO_VACIO = "";

    private final UsuarioControlador usuarioControlador;
    private final PlatoControlador platoControlador;
    private final Scanner entrada;

    public GuiCli(UsuarioControlador usuarioControlador, PlatoControlador platoControlador) {
        this(usuarioControlador, platoControlador, new Scanner(System.in));
    }

    GuiCli(UsuarioControlador usuarioControlador, PlatoControlador platoControlador, Scanner entrada) {
        this.usuarioControlador = usuarioControlador;
        this.platoControlador = platoControlador;
        this.entrada = entrada;
    }

    public int obtenerOpcionMenu() {
        do {
            mostrarMenu();
            String valorIngresado = limpiarEntrada(entrada.nextLine());
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_AGREGAR_USUARIO && opcion <= OPCION_SALIR) {
                    return opcion;
                }
            } catch (NumberFormatException exception) {
                // El flujo informa el valor invalido y vuelve a mostrar el menu.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA + "%n", valorIngresado);
        } while (true);
    }

    public void ejecutarAccion() {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu();
            try {
                switch (opcion) {
                    case OPCION_AGREGAR_USUARIO -> registrarUsuario();
                    case OPCION_BUSCAR_USUARIO -> mostrarUsuarioPorId();
                    case OPCION_MOSTRAR_TODOS_USUARIOS -> mostrarTodosLosUsuarios();
                    case OPCION_AGREGAR_PLATO -> registrarPlato();
                    case OPCION_BUSCAR_PLATO -> mostrarPlatoPorId();
                    case OPCION_LISTAR_PLATOS -> mostrarTodosLosPlatos();
                    case OPCION_ACTUALIZAR_PLATO -> actualizarPlato();
                    case OPCION_ELIMINAR_PLATO -> eliminarPlato();
                    case OPCION_SALIR -> continuar = false;
                }
            } catch (UsuarioInvalidoException
                     | UsuarioNoExisteException
                     | UsuarioYaExisteException
                     | PlatoInvalidoException
                     | PlatoNoExisteException
                     | PlatoYaExisteException exception) {
                System.out.println(MENSAJE_ERROR + exception.getMessage());
            }
        }
        System.out.println(MENSAJE_DESPEDIDA);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println(TEXTO_TITULO);
        System.out.println(SEPARADOR);
        System.out.println(OPCIONES);
        System.out.println(SEPARADOR);
        System.out.println("1 - Agregar Usuario");
        System.out.println("2 - Buscar Usuario por Id");
        System.out.println("3 - Ver todos los Usuarios");
        System.out.println("4 - Agregar Plato");
        System.out.println("5 - Buscar Plato por Id");
        System.out.println("6 - Ver todos los Platos");
        System.out.println("7 - Actualizar Plato");
        System.out.println("8 - Eliminar Plato");
        System.out.println("9 - Salir");
        System.out.print(TEXTO_SOLICITUD_OPCION);
    }

    // USUARIO (sin cambios de lógica)

    private void registrarUsuario() {
        usuarioControlador.registrar(capturarDatosUsuario());
        System.out.println(MENSAJE_REGISTRO_EXITOSO);
    }

    private void mostrarUsuarioPorId() {
        System.out.println(usuarioControlador.obtenerPorId(capturarId()));
    }

    private void mostrarTodosLosUsuarios() {
        ObtenerUsuarioResponse response = usuarioControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_VACIA);
            return;
        }
        System.out.println(response);
    }

    private RegistrarUsuarioPeticion capturarDatosUsuario() {
        System.out.println();
        System.out.println(TITULO_REGISTRO_USUARIO);
        return new RegistrarUsuarioPeticion(
                capturarId(), capturarPassword(), capturarNombre(), capturarEmail());
    }

    private String capturarId() {
        do {
            System.out.print(SOLICITUD_ID);
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new UsuarioId(id))) {
                return id;
            }
            System.out.println(MENSAJE_ID_INVALIDO);
        } while (true);
    }

    private String capturarPassword() {
        do {
            System.out.print(SOLICITUD_PASSWORD);
            String password = entrada.nextLine();
            if (esValido(() -> new Password(password))) {
                return password;
            }
            System.out.println(MENSAJE_PASSWORD_INVALIDO);
        } while (true);
    }

    private String capturarNombre() {
        do {
            System.out.print(SOLICITUD_NOMBRE);
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreUsuario(nombre))) {
                return nombre;
            }
            System.out.println(MENSAJE_NOMBRE_INVALIDO);
        } while (true);
    }

    private String capturarEmail() {
        do {
            System.out.print(SOLICITUD_EMAIL);
            String email = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new Email(email))) {
                return email;
            }
            System.out.println(MENSAJE_EMAIL_INVALIDO);
        } while (true);
    }

    //PLATO (nuevo)

    private void registrarPlato() {
        platoControlador.registrar(capturarDatosPlato());
        System.out.println(MENSAJE_REGISTRO_PLATO_EXITOSO);
    }

    private void mostrarPlatoPorId() {
        String id = capturarIdPlato();
        Plato plato = platoControlador.obtenerPorId(id);
        System.out.println(plato.getId() + " | " + plato.getNombre() + " | $" + plato.getPrecio());
    }

    private void mostrarTodosLosPlatos() {
        List<Plato> platos = platoControlador.obtenerTodos();
        if (platos.isEmpty()) {
            System.out.println(MENSAJE_LISTA_PLATOS_VACIA);
            return;
        }
        for (Plato plato : platos) {
            System.out.println(plato.getId() + " | " + plato.getNombre() + " | $" + plato.getPrecio());
        }
    }

    private void actualizarPlato() {
        platoControlador.actualizar(capturarDatosPlato());
        System.out.println(MENSAJE_ACTUALIZACION_PLATO_EXITOSA);
    }

    private void eliminarPlato() {
        String id = capturarIdPlato();
        platoControlador.eliminar(id);
        System.out.println(MENSAJE_ELIMINACION_PLATO_EXITOSA);
    }

    private RegistrarPlatoPeticion capturarDatosPlato() {
        System.out.println();
        System.out.println(TITULO_REGISTRO_PLATO);
        String id = capturarIdPlato();
        String nombre = capturarNombrePlato();
        double precio = capturarPrecio();
        return new RegistrarPlatoPeticion(id, nombre, precio);
    }

    private String capturarIdPlato() {
        System.out.print(SOLICITUD_ID);
        return limpiarEntrada(entrada.nextLine());
    }

    private String capturarNombrePlato() {
        System.out.print(SOLICITUD_NOMBRE_PLATO);
        return limpiarEntrada(entrada.nextLine());
    }

    private double capturarPrecio() {
        do {
            System.out.print(SOLICITUD_PRECIO);
            String valor = limpiarEntrada(entrada.nextLine());
            try {
                double precio = Double.parseDouble(valor);
                if (precio > 0) {
                    return precio;
                }
            } catch (NumberFormatException exception) {
                // cae al mensaje de invalido
            }
            System.out.println(MENSAJE_PRECIO_INVALIDO);
        } while (true);
    }

    //UTILIDADES

    private static String limpiarEntrada(String valor) {
        return valor.replace(MARCA_ORDEN_BYTES, TEXTO_VACIO).trim();
    }

    private static boolean esValido(Runnable validacion) {
        try {
            validacion.run();
            return true;
        } catch (UsuarioInvalidoException exception) {
            return false;
        }
    }
}