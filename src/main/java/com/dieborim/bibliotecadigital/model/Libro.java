package com.dieborim.bibliotecadigital.model;

import jakarta.persistence.*;

import java.util.Collections;

/**
 * Con Entity le estamos indicando a java que esta clase va a ser una tabla en una base de datos
 */
@Entity
/**
 * Con Table le decimos como se va a llamar esa tabla en la base de datos
 */
@Table(name = "libros")
public class Libro {
    @Id //Con esto indicamos que este va a ser el identificador de nuestra tabla
    /**
     * //Con GeneratedValue le decimos a la base de datos que este campo se va a autogenerar automáticamente
     * (en este caso es un ID que se incrementa solo)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    /**
     * Con Column hacemos que este atributo tenga un valor único en la tabla de la bd, en este caso
     * los títulos no se van a repetir
     */
    @Column(unique = true)
    private String titulo;
    /**
     * Con Enumerated indicamos que este atributo viene de un enum
     */
    @Enumerated(EnumType.STRING)
    private Idiomas idioma;
    private Integer numeroDescargas;
    @ManyToOne
    private Autor autor;

    public Libro(){
        /**
         * JPA necesita un constructor vacío para poder crear objetos automáticamente al traer datos de la base
         */
    }

    public Libro(DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.idioma = Idiomas.fromList(datosLibro.idiomas());
        this.numeroDescargas = datosLibro.numeroDescargas();
        /**
         * El constructor del libro recibe un objeto Autor para establecer
         * la relación ManyToOne entre Libro y Autor. Aunque no se vea
         * explícitamente una columna con el nombre del autor en la tabla,
         * Hibernate genera una columna 'autor_id' como clave foránea
         * que conecta este libro con su autor correspondiente.
         */
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getIdiomas() {
        return idioma.getNombreIdioma();
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idioma = idiomas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
