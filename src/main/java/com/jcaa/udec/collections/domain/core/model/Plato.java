package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.NombrePlato;
import com.jcaa.udec.collections.domain.core.valueobject.PlatoId;
import com.jcaa.udec.collections.domain.core.valueobject.Precio;
import lombok.Builder;

public class Plato {
    private final PlatoId id;
    private final NombrePlato nombre;
    private final Precio precio;

    @Builder
    public Plato(String id, String nombre, double precio) {
        this.id = new PlatoId(id);
        this.nombre = new NombrePlato(nombre);
        this.precio = new Precio(precio);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public double getPrecio() {
        return precio.valor();
    }
}