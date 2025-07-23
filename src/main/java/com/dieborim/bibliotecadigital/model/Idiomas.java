package com.dieborim.bibliotecadigital.model;

import java.util.List;

public enum Idiomas {

    /**
     * Un enum es un tipo especial de clase que se usa para definir un
     * conjunto fijo de constantes.
     */
    en ("inglés"),
    fr ("francés"),
    de ("alemán"),
    es ("español"),
    it ("italiano"),
    fi ("finés"),
    sv ("sueco"),
    pt ("portugués"),
    la ("latín"),
    UNKNOWN("desconocido"); // Nuevo idioma por defecto

    private final String nombreIdioma;

    Idiomas(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    /**
     * Este método toma uno de códigos de idioma (por ejemplo "en", "es")
     * y devuelve el primero que coincida con los que tiene el enum.
     * Si el idioma no está soportado, lanza una excepción.
     */
    public static Idiomas fromCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return UNKNOWN;
        }
        try {
            return Idiomas.valueOf(codigo); // ej: "en"
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }


    public String getNombreIdioma() {
        return nombreIdioma;
    }
}
