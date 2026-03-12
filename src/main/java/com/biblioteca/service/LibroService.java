package com.biblioteca.service;

import java.util.Optional;

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
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    // POST - crear libro
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // PUT - actualizar libro
    public Libro actualizarLibro(Long id, Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    // DELETE - eliminar libro
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}