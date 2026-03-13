package com.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Spring genera la consulta automáticamente basándose en el nombre del método
    boolean existsByTituloAndAutor(String titulo, String autor);
}