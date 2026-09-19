package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.PlatoInvalidoException;
import java.util.Objects;

public record PlatoId(String valor) {
    public PlatoId {
        if (Objects.isNull(valor) || valor.isBlank()) {
            throw new PlatoInvalidoException();
        }
    }
}