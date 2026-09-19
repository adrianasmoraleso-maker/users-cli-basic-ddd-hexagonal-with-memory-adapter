# 🧸 Sistema de Gestión para Guardería - Arquitectura Hexagonal

## 📋 Información Académica del Estudiante

| Campo | Detalle |
| :--- | :--- |
| **Estudiante** | Adriana Morales O. |
| **Identificación Académica** | 3er Semestre |
| **Institución** | Universidad de Cartagena |
| **Programa Académico** | Ingeniería de Software |
| **Asignatura** | Desarrollo de Software |
| **Tutor / Docente** | John Carlos Arrieta Arrieta |

---

## 🏫 Sistema Desarrollado (Caso de Estudio: Guardería)

El sistema desarrollado corresponde a una plataforma de gestión centralizada para una **Guardería Infantil (CEA)**. Su propósito principal es administrar de forma limpia y desacoplada el registro de niños, la gestión de menús alimenticios (platos), y el control de adultos autorizados y responsables de pagos, aplicando estrictamente principios de Arquitectura Hexagonal y Domain-Driven Design (DDD) sin el uso de frameworks pesados como Spring ni persistencia externa. Para este caso de estudio en específico, se implementó el caso de uso del dominio de platos para validar su correcto funcionamiento a través de la interfaz de línea de comandos (CLI), siguiendo la estructura de la arquitectura establecida.

---

## 🛠️ Tecnologías y Requisitos

* **Lenguaje:** Java 17+ (probado con OpenJDK Temurin 25.0.4 LTS)
* **Compilador:** `javac 25.0.4`
* **Gestor de Dependencias y Construcción:** Apache Maven 3.9.16 (Bundled con IntelliJ IDEA)
* **Paradigma:** Orientación a Objetos / Arquitectura Hexagonal (Ports & Adapters)
* **Entorno de Ejecución:** Persistencia en memoria (sin bases de datos externas, ORM ni frameworks como Spring)

## 📦 Dependencias Principales
El proyecto gestiona sus dependencias mediante Maven e incluye:
* **Lombok (`1.18.38`):** Reducción de código repetitivo (getters, setters, constructores).
* **JUnit 5 (`5.12.2`):** Framework para pruebas unitarias.
* **AssertJ (`3.27.3`):** Aseveraciones fluidas para las pruebas.
* **Logback (`1.5.33`):** Gestión de registros (logging).
* **JaCoCo (`0.8.12`):** Plugin para análisis de cobertura de código.

*(Se consultar el detalle completo de la configuración y plugins en el archivo `pom.xml` del repositorio).*

---

## 🎯 Casos de Uso Integrados al Fork

Ahora la aplicación permite gestionar también los platos de alimentación infantil desde una interfaz de línea de comandos:

* **Gestión de Platos/Menús:**
    * Registrar platos de alimentación para los niños.
    * Buscar platos específicos.
    * Actualizar y eliminar registros de menús.
* **Persistencia:** Los datos se almacenan de manera síncrona en estructuras de memoria RAM durante la ejecución de la aplicación.

---

## 🏗️ Arquitectura Hexagonal

El proyecto aplica arquitectura hexagonal estricta para mantener las reglas de negocio completamente aisladas e independientes de la interfaz de usuario y de los mecanismos de almacenamiento.

### Estructura del Proyecto

```text
src/main/java/com/jcaa/udec/
├── 📄 Main.java
└── 📁 collections/
    ├── 📁 domain/                          # CAPA DE DOMINIO (Reglas de Negocio Puras)
    │   ├── 📁 core/
    │   │   ├── 📁 model/
    │   │   │   ├── 📄 Usuario.java
    │   │   │   └── 📄 Plato.java
    │   │   ├── 📁 valueobject/
    │   │   │   ├── 📄 UsuarioId.java
    │   │   │   ├── 📄 NombreUsuario.java
    │   │   │   ├── 📄 Password.java
    │   │   │   ├── 📄 Email.java
    │   │   │   ├── 📄 PlatoId.java
    │   │   │   ├── 📄 NombrePlato.java
    │   │   │   └── 📄 Precio.java
    │   │   └── 📁 exception/
    │   │       ├── 📄 UsuarioInvalidoException.java
    │   │       ├── 📄 UsuarioNoExisteException.java
    │   │       ├── 📄 UsuarioYaExisteException.java
    │   │       ├── 📄 PlatoInvalidoException.java
    │   │       ├── 📄 PlatoNoExisteException.java
    │   │       └── 📄 PlatoYaExisteException.java
    │   └── 📁 port/
    │       └── 📁 out/                     # Puertos de Salida (Persistencia/SPI)
    │           ├── 📄 GuardarUsuarioPort.java
    │           ├── 📄 ObtenerUsuariosPort.java
    │           ├── 📄 GuardarPlatoPort.java
    │           ├── 📄 ObtenerPlatosPort.java
    │           ├── 📄 ActualizarPlatoPort.java
    │           └── 📄 EliminarPlatoPort.java
    ├── 📁 application/                     # CAPA DE APLICACION (Orquestacion de Casos de Uso)
    │   └── 📁 service/
    │       ├── 📄 AgregarUsuarioService.java
    │       ├── 📄 ObtenerUsuariosService.java
    │       ├── 📄 AgregarPlatoService.java
    │       ├── 📄 BuscarPlatoService.java
    │       ├── 📄 ActualizarPlatoService.java
    │       ├── 📄 EliminarPlatoService.java
    │       ├── 📁 ports/in/                # Puertos de Entrada (Casos de Uso)
    │       │   ├── 📄 AgregarUsuarioUseCase.java
    │       │   ├── 📄 ObtenerUsuarioUseCase.java
    │       │   ├── 📄 AgregarPlatoUseCase.java
    │       │   ├── 📄 BuscarPlatoUseCase.java
    │       │   ├── 📄 ActualizarPlatoUseCase.java
    │       │   └── 📄 EliminarPlatoUseCase.java
    │       ├── 📁 dto/
    │       │   ├── 📁 command/
    │       │   │   ├── 📄 CrearUsuarioComando.java
    │       │   │   └── 📄 CrearPlatoComando.java
    │       │   └── 📁 query/
    │       │       └── 📄 ObtenerUsuarioConsulta.java
    │       └── 📁 mapper/
    │           ├── 📄 UsuarioMapper.java
    │           └── 📄 PlatoMapper.java
    ├── 📁 adapter/
    │   └── 📁 persistence/
    │       └── 📁 memory/                  # ADAPTADOR DE SALIDA (Persistencia en Memoria)
    │           ├── 📄 UsuariosMemoria.java
    │           ├── 📄 GuardarUsuarioAdapter.java
    │           ├── 📄 ObtenerUsuariosAdapter.java
    │           ├── 📄 PlatosMemoria.java
    │           ├── 📄 GuardarPlatoAdapter.java
    │           ├── 📄 ObtenerPlatosAdapter.java
    │           ├── 📄 ActualizarPlatoAdapter.java
    │           └── 📄 EliminarPlatoAdapter.java
    └── 📁 entrypoint/                      # ADAPTADORES DE ENTRADA (CLI y Controladores)
        ├── 📁 cli/
        │   └── 📄 GuiCli.java
        └── 📁 controller/
            ├── 📄 UsuarioControlador.java
            ├── 📄 UsuarioControladorImpl.java
            ├── 📄 PlatoControlador.java
            ├── 📄 PlatoControladorImpl.java
            ├── 📁 dto/
            │   ├── 📁 request/
            │   │   ├── 📄 RegistrarUsuarioPeticion.java
            │   │   └── 📄 RegistrarPlatoPeticion.java
            │   └── 📁 response/
            │       ├── 📄 UsuarioResponse.java
            │       └── 📄 ObtenerUsuarioResponse.java
            └── 📁 mapper/
                └── 📄 UsuarioResponseMapper.java
```

---

## 🏛️ Detalle de las Capas del Repositorio

### Dominio (`domain`)
Contiene los modelos de negocio puros (`Usuario`, `Plato`), las excepciones de dominio y los Value Objects que blindan las invariantes del sistema de la guardería:
* **Value Objects incluidos:** `UsuarioId`, `NombreUsuario`, `Password`, `Email`, `PlatoId`, `NombrePlato`, `Precio`.
* **Puertos de Salida (`port/out`):** Interfaces que definen los contratos que la infraestructura de persistencia debe cumplir para guardar y consultar entidades sin acoplar el dominio a tecnologías concretas.

### Aplicación (`application`)
Contiene los servicios de aplicación encargados de orquestar los casos de uso del negocio y coordinar la lógica transaccional y de flujos:
* **Puertos de Entrada (`ports/in`):** Interfaces de casos de uso expuestas hacia el exterior (`AgregarUsuarioUseCase`, `AgregarPlatoUseCase`, etc.).
* **DTOs y Mappers:** Objetos de transferencia de comandos y consultas (`CrearUsuarioComando`, `ObtenerUsuarioConsulta`) acompañados de sus respectivos mappers.

### Adaptadores (`adapter`)
Implementan los mecanismos de salida hacia tecnologías externas. En este repositorio se configuró un adaptador de persistencia en memoria volátil:
* **Persistencia en Memoria (`persistence/memory`):** Clases como `UsuariosMemoria` y `PlatosMemoria` que simulan el comportamiento de una base de datos mediante colecciones de Java, garantizando que no se usen mocks sino implementaciones reales no durables.

### Entrypoints (`entrypoint`)
Puntos de entrada primarios de la aplicación que interactúan directamente con el usuario externo:
* **CLI y Controladores:** Incluye `GuiCli` y los controladores (`UsuarioControlador`, `PlatoControlador`) encargados de recibir las peticiones de consola, mapearlas a DTOs y enviarlas a los casos de uso correspondientes.

---

## 💡 Decisiones Pedagógicas

* Las dependencias de todo el sistema se ensamblan y conectan de forma manual directamente en la clase `Main`.
* **No se utiliza Spring Framework** ni ningún contenedor pesado de Inyección de Dependencias automático.
* **No se utiliza ninguna base de datos** relacional o no relacional externa.
* **No se utiliza ORM** (como Hibernate o JPA).
* **No se utiliza caché.**
* La persistencia en memoria implementada permite observar con total transparencia y claridad el flujo de datos de extremo a extremo entre las diferentes capas de la arquitectura hexagonal.

---

## ⚙️ Requisitos de Ejecución

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Apache Maven:** Versión 3.9 o superior.