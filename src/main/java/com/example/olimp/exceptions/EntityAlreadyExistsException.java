package com.example.olimp.exceptions;

/**
 * Выбрасывается, когда пользователь пытается добавить сущность с существующим id
 */
public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
