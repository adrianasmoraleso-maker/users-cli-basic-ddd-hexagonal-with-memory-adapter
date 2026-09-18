package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.PlatoInvalidoException;
import java.util.Objects;

public record NombrePlato(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombrePlato {
        if (Objects.isNull(valor) || valor.isBlank() || valor.length() < LONGITUD_MINIMA) {
            throw new PlatoInvalidoException();
        }
    }
}