package com.dieborim.bibliotecadigital.repository;

import com.dieborim.bibliotecadigital.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Libro es el tipo de objeto que vamos a usar y long el tipo de dato de nuestro identificador
 */
public interface LibroRepository extends JpaRepository <Libro,Long>{

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
}
