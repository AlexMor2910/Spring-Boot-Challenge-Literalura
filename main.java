package com.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BookService bookService, AuthorService authorService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("\n--- MENÚ LITERALURA ---");
                System.out.println("1. Buscar libro por título");
                System.out.println("2. Listar todos los libros");
                System.out.println("3. Listar autores");
                System.out.println("4. Listar autores vivos en determinado año");
                System.out.println("5. Mostrar cantidad de libros por idioma");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        bookService.buscarLibroPorTitulo(titulo);
                        break;
                    case 2:
                        bookService.listarLibros();
                        break;
                    case 3:
                        authorService.listarAutores();
                        break;
                    case 4:
                        System.out.print("Ingrese el año para verificar autores vivos: ");
                        int anio = scanner.nextInt();
                        authorService.autoresVivosEn(anio);
                        break;
                    case 5:
                        System.out.print("Ingrese idioma (por ejemplo: 'en', 'es'): ");
                        String idioma = scanner.nextLine();
                        bookService.contarLibrosPorIdioma(idioma);
                        break;
                    case 0:
                        System.out.println("Gracias por usar LiterAlura. ");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 0);
        };
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("https://gutendex.com/books/").build();
    }
}