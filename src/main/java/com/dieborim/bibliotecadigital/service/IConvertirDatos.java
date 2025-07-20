package com.dieborim.bibliotecadigital.service;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
    /**
     * <T> es un tipo genérico. Significa que este método puede trabajar con cualquier tipo de dato.
     * Recibe dos cosas:
     * Un String json → una cadena con datos en formato JSON
     * Una Class<T> clase → la clase a la que queremos convertir ese JSON
     * Devuelve un objeto del tipo T, que es el resultado convertido desde JSON a un objeto Java
     */
}
