package com.dieborim.bibliotecadigital;

import com.dieborim.bibliotecadigital.principal.Principal;
import com.dieborim.bibliotecadigital.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaDigitalApplication implements CommandLineRunner {

	/**
	 * Autowired sirve para hacer la inyección de dependencias, lo cual significa que no necesitamos
	 * crear manualmente un new LibroRepository(), Spring lo hace por nosotros
	 */
	@Autowired
	private LibroRepository repository;
	/*
    Spring detecta la interfáz y ejecuta el método run() justo después de levantar el contexto de la aplicación
    (es decir, "Cuando la app arranque, quiero que me dejes correr un bloque de código al inicio")
     */
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaDigitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**
		 * Aquí le pasamos el repository a nuestra clase principal para que pueda usar los métodos de LibroRepository
		 */
		Principal principal = new Principal(repository);
		principal.muestraElMenu();

	}
}
