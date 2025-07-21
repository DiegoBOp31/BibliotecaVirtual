package com.dieborim.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResults(
        @JsonAlias("results") List<DatosLibro> listaDeLibros
        ) {
    /**
     * Aquí solo mando a llamar la lista completa de libros que me regresa la búsqueda
     */
}
