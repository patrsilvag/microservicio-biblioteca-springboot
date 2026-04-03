package com.biblioteca.exception;

/**
 * Excepción personalizada para errores de lógica de negocio. Se utiliza para lanzar errores de
 * validación, duplicados o reglas incumplidas.
 */
public class BusinessRuleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String message) {
        super(message);
    }
}
