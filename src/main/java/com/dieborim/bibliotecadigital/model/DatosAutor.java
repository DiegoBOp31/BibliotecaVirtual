package com.dieborim.bibliotecadigital.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * JsonIgnorePropeties sirve para ignorar todos los demás datos que no hemos mapeado en nuestro record
 */
public record DatosAutor(
        /**
         * JsonAlias sirve para hacer una vinculación de los datos de la api a nuestro mapeo
         * En este caso, "name" se encuentra en la API y se refiere al nombre del autor. Y "nombre"
         * es como nosotros le vamos a llamar a ese dato
         */
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaFallecimiento) {
    /**
     * Aquí estamos mapeando los datos que vamos a obtener de la api que estamos consultando
     * Sólo mando a llamar los datos del autor
     */
}
