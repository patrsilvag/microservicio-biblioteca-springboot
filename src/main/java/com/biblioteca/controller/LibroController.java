package com.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // GET - obtener libro por ID
    @GetMapping("/{id}")
    public Optional<Libro> obtenerLibro(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id);
    }

    // POST - crear libro
    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.crearLibro(libro);
    }

    // PUT - actualizar libro
    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro);
    }

    // DELETE - eliminar libro
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}