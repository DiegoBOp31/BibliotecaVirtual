package com.dieborim.bibliotecadigital.repository;

import com.dieborim.bibliotecadigital.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
