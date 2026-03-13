package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;

@Service
@SuppressWarnings("null") // Elimina las advertencias de conversión de Long y Libro
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // GET - obtener libro por ID
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el libro con ID: " + id));
    }

    // POST - crear libro
    public Libro crearLibro(Libro libro) {
        // 1. Usamos existsBy para verificar presencia sin importar cuántos haya
        if (libroRepository.existsByTituloAndAutor(libro.getTitulo(), libro.getAutor())) {
            throw new RuntimeException(
                    "El libro '" + libro.getTitulo() + "' de " + libro.getAutor()
                            + " ya está registrado en el sistema.");
        }

        // 2. Forzamos el ID a null para que sea siempre un INSERT
        libro.setId(null);
        return libroRepository.save(libro);
    }

    // PUT - actualizar libro
    public Libro actualizarLibro(Long id, Libro libro) {
        // 1. Validamos si el libro existe antes de intentar cualquier cosa
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("No se puede actualizar: El libro con ID " + id + " no existe.");
        }

        // 2. Si existe, nos aseguramos de que el objeto tenga el ID correcto
        libro.setId(id);

        // 3. Guardamos los cambios
        return libroRepository.save(libro);
    }

    // DELETE - eliminar libro
    public void eliminarLibro(Long id) {
        // 1. Validamos primero si existe
        if (!libroRepository.existsById(id)) {
            // 2. Si no existe, lanzamos la excepción que el Global Handler atrapará
            throw new RuntimeException("No se puede eliminar: El libro con ID " + id + " no existe.");
        }
        // 3. Si existe, procedemos al borrado
        libroRepository.deleteById(id);
    }
}