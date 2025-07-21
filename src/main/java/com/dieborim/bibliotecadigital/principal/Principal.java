package com.dieborim.bibliotecadigital.principal;

import com.dieborim.bibliotecadigital.model.DatosAutor;
import com.dieborim.bibliotecadigital.model.DatosLibro;
import com.dieborim.bibliotecadigital.model.DatosResults;
import com.dieborim.bibliotecadigital.repository.LibroRepository;
import com.dieborim.bibliotecadigital.service.ConsumoAPI;
import com.dieborim.bibliotecadigital.service.ConvertirDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    /**
     * La palabra final nos indica que está va a ser una constante siempre
     */
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
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
            eleccionDelUsuario = teclado.nextInt();
            teclado.nextLine();

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
        if(libroEncontrado.isPresent()){
            System.out.println("Libro encontrado");
            Optional<DatosAutor> autorDelLibro = libroEncontrado.get().autores().stream()
                    .findFirst();
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
