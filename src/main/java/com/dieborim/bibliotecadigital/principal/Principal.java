package com.dieborim.bibliotecadigital.principal;

import com.dieborim.bibliotecadigital.model.*;
import com.dieborim.bibliotecadigital.repository.AutorRepository;
import com.dieborim.bibliotecadigital.repository.LibroRepository;
import com.dieborim.bibliotecadigital.service.ConsumoAPI;
import com.dieborim.bibliotecadigital.service.ConvertirDatos;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    /**
     * La palabra final nos indica que está va a ser una constante siempre
     */
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }

    public void muestraElMenu(){
        var eleccionDelUsuario = -1;
        while (eleccionDelUsuario != 0){
            String menu = """
                ------------------------------
                Elija una opción del menú:
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4. Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                ------------------------------
                """;
            System.out.println(menu);
            //Validación para entradas que no son números
            try{
                eleccionDelUsuario = teclado.nextInt();
                teclado.nextLine();
            }catch (InputMismatchException e){
                teclado.nextLine();
            }
            switch (eleccionDelUsuario){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    //Forzar cierre de la app
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibro() {
        System.out.println("Escribe el nombre del libro que quieres buscar");
        //Leemos la entrada del libro que quiere buscar el usuario
        var libroBuscado = teclado.nextLine();
        //Hacemos la petición de los datos a la api
        var json = consumoAPI.obtenerDatos(URL_BASE+libroBuscado.replace(" ","%20"));
//        System.out.println(json);
        //Convertimos el json obtenido en un objeto de la clase DatosResults
        var datosResults = convertirDatos.obtenerDatos(json, DatosResults.class);
        /**
         * Optional es una forma segura de manejar valores que pueden o no estar presentes
         * Con una función lambda hacemos que tome el título de cada libro que regresa la búsqueda
         * La convertimos en mayúsculas. Compara si el título contiene el texto buscado y lo convierte
         * a mayúsculas también. Y por último cuando encuentra el primer libro que cumpla con la condición,
         * lo devuelve.
         */
        Optional<DatosLibro> libroEncontrado = datosResults.listaDeLibros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(libroBuscado.toUpperCase()))
                .findFirst();
//        System.out.println(libroEncontrado.get());

        /**
         *'libroEncontrado' es un Optional, lo que significa que puede contener un libro o estar vacío.
         *Primero verificamos si hay un libro presente con 'isPresent()'.
         */
        if(libroEncontrado.isPresent()){
            /**
             *Si sí lo hay, usamos '.get()' para obtener el objeto DatosLibro real dentro del Optional.
             *Es importante hacer esta verificación antes de usar '.get()', para evitar errores si no se encuentra ningún libro.
             */
            var datosLibro = libroEncontrado.get();

            //Validación para revisar si es que ya existe el libro en la db o no
            if (libroRepositorio.findByTituloContainsIgnoreCase(datosLibro.titulo()).isPresent()) {
                System.out.println("El libro ya existe en la base de datos.");
                return; // Esto te saca del método actual y te regresa al menú
            }
            /**
             *Inicializamos la variable 'autor' con null para evitar el error de compilación. Java es muy estricto con
             * las variables locales y necesita estar 100% seguro de que la variable será inicializada en todas las
             * posibles rutas del código, así que le damos un valor inicial para que esté satisfecho.
             */
            Autor autor = null;
            if (!datosLibro.autores().isEmpty()){
                var datosAutor = datosLibro.autores().get(0);
                autor = autorRepositorio.findByNombreContainsIgnoreCase(datosAutor.nombre())
                        .orElseGet(() -> autorRepositorio.save(new Autor(datosAutor)));
            }
            // Creamos una instancia de la entidad Libro usando los datos que vinieron de la API
            var libro = new Libro(datosLibro, autor);
            // Guardamos el libro (y su relación con autores) en la base de datos
            libroRepositorio.save(libro);
            // Recuperamos el libro ya guardado desde la base de datos (para mostrar datos confirmados)
            var libroGuardado = libroRepositorio.findByTituloContainsIgnoreCase(libro.getTitulo());
            System.out.println("----------LIBRO----------");
            System.out.println("Título: "+libroGuardado.get().getTitulo());
            System.out.println("Autor: "+libroGuardado.get().getAutor().getNombre());
            System.out.println("Idioma: "+libroGuardado.get().getIdiomas());
            System.out.println("Número de descargas: "+libroGuardado.get().getNumeroDescargas());
            System.out.println("----------*****----------");
        }else {
            System.out.println("Libro no encontrado");
        }

    }

    private void listarLibrosRegistrados() {

    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivosPorAnio() {
    }

    private void listarLibrosPorIdioma() {
    }








}
