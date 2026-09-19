package com.jcaa.udec.collections.domain.core.exception;

public class PlatoNoExisteException extends RuntimeException {
    public PlatoNoExisteException(String id) {
        super("El plato con id " + id + " no existe");
    }
}