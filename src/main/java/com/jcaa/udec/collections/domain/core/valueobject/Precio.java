package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.PlatoInvalidoException;

public record Precio(double valor) {
    public Precio {
        if (valor <= 0) {
            throw new PlatoInvalidoException();
        }
    }
}