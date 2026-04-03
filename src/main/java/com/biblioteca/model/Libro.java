package com.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Libro")
@Data // Genera automáticamente Getters, Setters, toString, etc.
@NoArgsConstructor // Genera automáticamente el constructor vacío para JPA
@AllArgsConstructor // Genera automáticamente el constructor con todos los campos para el Builder
@Builder // Implementación del Patrón de Diseño solicitado
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres.")
    @Column(name = "TITULO")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    @Column(name = "AUTOR")
    private String autor;

    @NotNull(message = "El año de publicación no puede ser nulo.")
    @Min(value = 1000, message = "El año debe ser mayor a 1000.")
    @Column(name = "ANIO_PUBLICACION")
    private int anioPublicacion;

    @NotBlank(message = "El género no puede estar vacío")
    @Column(name = "GENERO")
    private String genero;

    // NO ESCRIBIR CONSTRUCTORES, GETTERS NI SETTERS AQUÍ.
    // Lombok se encarga de todo gracias a las anotaciones superiores.
}
