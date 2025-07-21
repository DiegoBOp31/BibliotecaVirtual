package com.dieborim.bibliotecadigital.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private Idiomas idiomas;
    private Integer numeroDescargas;
    @ManyToMany
    @JoinTable(name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();

    public Libro(){
        /**
         * JPA necesita un constructor vacío para poder crear objetos automáticamente al traer datos de la base
         */
    }

    /**
     * Este método sirve para sincronizar la relación ManyToMany entre libros
     * y autores desde el lado de la entidad Libro.
     */
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idiomas = Idiomas.fromList(datosLibro.idiomas());
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
        autor.getLibros().add(this);
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
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
