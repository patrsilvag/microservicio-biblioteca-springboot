package com.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.exception.BusinessRuleException;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;

@Service
@SuppressWarnings("null")
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    /**
     * GET - Obtener todos los libros registrados.
     */
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    /**
     * GET - Obtener un libro por su ID único. Lanza ResourceNotFoundException para que el Handler
     * devuelva 404.
     */
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el libro con ID: " + id));
    }

    /**
     * POST - Crear un nuevo libro utilizando el Patrón Builder. Incluye normalización de strings y
     * validación de duplicados (409 Conflict).
     */
    public Libro crearLibro(Libro libro) {
        // NORMALIZACIÓN
        String tituloNorm = libro.getTitulo().trim().toUpperCase();
        String autorNorm = libro.getAutor().trim().toUpperCase();

        // VALIDACIÓN DE DUPLICADOS
        if (libroRepository.existsByTituloAndAutor(tituloNorm, autorNorm)) {
            throw new BusinessRuleException("El libro '" + libro.getTitulo().trim() + "' de "
                    + libro.getAutor().trim() + " ya está registrado.");
        }

        // IMPLEMENTACIÓN PATRÓN BUILDER
        Libro libroAGuardar = Libro.builder().titulo(tituloNorm).autor(autorNorm)
                .anioPublicacion(libro.getAnioPublicacion()).genero(libro.getGenero()).build(); // El
                                                                                                // ID
                                                                                                // queda
                                                                                                // nulo
                                                                                                // para
                                                                                                // que
                                                                                                // Oracle
                                                                                                // genere
                                                                                                // uno
                                                                                                // nuevo

        return libroRepository.save(libroAGuardar);
    }

    /**
     * PUT - Actualizar un libro existente utilizando el Patrón Builder. Garantiza que el ID de la
     * URL sea el que prevalezca.
     */
    public Libro actualizarLibro(Long id, Libro libro) {
        // 1. Validamos existencia (404 si no existe)
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede actualizar: El libro con ID " + id + " no existe.");
        }

        // 2. Reconstruimos el objeto con Builder asegurando el ID correcto
        Libro libroActualizado = Libro.builder().id(id)
                .titulo(libro.getTitulo().trim().toUpperCase())
                .autor(libro.getAutor().trim().toUpperCase())
                .anioPublicacion(libro.getAnioPublicacion()).genero(libro.getGenero()).build();

        // 3. Guardamos los cambios
        return libroRepository.save(libroActualizado);
    }

    /**
     * DELETE - Eliminar un libro por ID.
     */
    public void eliminarLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede eliminar: El libro con ID " + id + " no existe.");
        }
        libroRepository.deleteById(id);
    }
}
