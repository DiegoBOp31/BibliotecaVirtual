package com.dieborim.bibliotecadigital;

import com.dieborim.bibliotecadigital.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaDigitalApplication implements CommandLineRunner {
	/*
    Spring detecta la interfáz y ejecuta el método run() justo después de levantar el contexto de la aplicación
    (es decir, "Cuando la app arranque, quiero que me dejes correr un bloque de código al inicio")
     */
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaDigitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();

	}
}
