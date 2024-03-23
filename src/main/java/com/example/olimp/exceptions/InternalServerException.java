package com.example.olimp.exceptions;

/**
 * Выбрасывается, в случае необработанных ошибок
 */
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message) {
        super(message);
    }
}
