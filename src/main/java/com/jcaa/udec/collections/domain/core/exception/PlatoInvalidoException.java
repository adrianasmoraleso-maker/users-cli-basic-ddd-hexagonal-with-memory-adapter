package com.jcaa.udec.collections.domain.core.exception;

public class PlatoInvalidoException extends RuntimeException {
    public PlatoInvalidoException() {
        super("Datos de plato invalidos");
    }
}