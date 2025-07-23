package com.dieborim.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") String autor,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Integer numeroDescargas
) {
    /**
     * Aquí estamos mapeando los datos que vamos a obtener de la api que estamos consultando
     * Aquí llamamos los datos del libro que queremos buscar
     */
}
