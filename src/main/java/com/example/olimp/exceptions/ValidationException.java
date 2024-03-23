package com.example.olimp.exceptions;

/**
 * Ошибка валидации
 */
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
