// SERVICIO: AuthorService
package com.literAlura.service;

import com.literAlura.model.Autor;
import com.literAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AutorRepository autorRepository;

    public AuthorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    public void autoresVivosEn(int anio) {
        List<Autor> vivos = autorRepository.findAutoresVivosEn(anio);
        if (vivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            vivos.forEach(System.out::println);
        }
    }
}

// SERVICIO: BookService
package com.literAlura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literAlura.model.Autor;
import com.literAlura.model.Libro;
import com.literAlura.repository.AutorRepository;
import com.literAlura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public BookService(LibroRepository libroRepository, AutorRepository autorRepository, WebClient webClient) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.webClient = webClient;
    }

    public void buscarLibroPorTitulo(String titulo) {
        String respuestaJson = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("search", titulo).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode root = mapper.readTree(respuestaJson);
            JsonNode primero = root.path("results").get(0);

            String tituloLibro = primero.path("title").asText();
            String idioma = primero.path("languages").get(0).asText();
            int descargas = primero.path("download_count").asInt();

            JsonNode autorNode = primero.path("authors").get(0);
            String nombreAutor = autorNode.path("name").asText();
            int nacimiento = autorNode.path("birth_year").asInt();
            JsonNode fallecimientoNode = autorNode.path("death_year");
            Integer fallecimiento = fallecimientoNode.isNull() ? null : fallecimientoNode.asInt();

            Autor autor = new Autor(nombreAutor, nacimiento, fallecimiento);
            autorRepository.save(autor);

            Libro libro = new Libro(tituloLibro, idioma, descargas, autor);
            libroRepository.save(libro);

            System.out.println("Libro guardado: " + libro);

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al procesar la respuesta de la API: " + e.getMessage());
        }
    }

    public void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    public void contarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        System.out.println("Cantidad de libros en idioma '" + idioma + "': " + libros.size());
    }
}
