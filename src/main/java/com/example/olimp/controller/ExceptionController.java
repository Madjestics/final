package com.example.olimp.controller;

import com.example.olimp.exceptions.EntityAlreadyExistsException;
import com.example.olimp.exceptions.EntityNotFoundException;
import com.example.olimp.exceptions.InternalServerException;
import com.example.olimp.exceptions.ValidationException;
import com.example.olimp.model.ResponseExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException exception) {
        return new ResponseExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseExceptionEntity handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseExceptionEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseExceptionEntity handleValidationException(ValidationException exception) {
        return new ResponseExceptionEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseExceptionEntity handleInternalServerException(InternalServerException exception) {
        return new ResponseExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
