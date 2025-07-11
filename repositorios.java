package com.literAlura.repository;

import com.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fallecimiento IS NULL OR a.fallecimiento > :anio")
    List<Autor> findAutoresVivosEn(int anio);
}

// REPOSITORIO: LibroRepository
package com.literAlura.repository;

import com.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);
}
