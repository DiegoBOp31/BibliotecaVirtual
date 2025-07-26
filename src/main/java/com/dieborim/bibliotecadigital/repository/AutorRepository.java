package com.dieborim.bibliotecadigital.repository;

import com.dieborim.bibliotecadigital.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    /**
     *  * Este repositorio se encarga de gestionar todas las operaciones relacionadas con la entidad Autor.
     *  * Es útil para verificar si un autor ya existe antes de insertarlo, evitando duplicados en la base de datos.
     *  * También permite realizar búsquedas, ediciones o eliminaciones directamente sobre los autores,
     *  * lo cual es importante cuando hay una relación muchos-a-muchos entre libros y autores.
     *  * Tener este repositorio separado ayuda a mantener una sincronización correcta entre ambas entidades,
     *  * especialmente cuando un autor puede estar asociado a varios libros distintos.
     */
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    /**
     * Esta consulta personalizada busca todos los autores cuya fecha de nacimiento y fecha de fallecimiento
     * abarcan un año específico (el año ingresado por el usuario).
     * Es decir, selecciona a los autores que estaban vivos durante ese año.
     * Utiliza la cláusula BETWEEN para verificar si el valor de ":anio" se encuentra
     * entre fechaNacimiento y fechaFallecimiento de cada autor (esas son variables de la entidad).
     */
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.fechaNacimiento AND a.fechaFallecimiento")
    List<Autor> encontrarAutoresVivosEnAnio(Integer anio);


}
