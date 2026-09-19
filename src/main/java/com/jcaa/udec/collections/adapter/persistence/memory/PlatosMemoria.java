package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Plato;

import java.util.ArrayList;
import java.util.List;

final class PlatosMemoria {
    private static final List<Plato> PLATOS = new ArrayList<>();

    private PlatosMemoria() {
    }

    static List<Plato> obtenerPlatos() {
        return PLATOS;
    }
}