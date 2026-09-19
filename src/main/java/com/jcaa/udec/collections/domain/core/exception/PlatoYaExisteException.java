package com.jcaa.udec.collections.domain.core.exception;

public class PlatoYaExisteException extends RuntimeException {
    public PlatoYaExisteException(String id) {
        super("El plato con id " + id + " ya existe");
    }
}