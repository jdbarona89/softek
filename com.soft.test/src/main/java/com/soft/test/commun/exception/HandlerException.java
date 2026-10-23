package com.soft.test.commun.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(IdDeClienteNoValidoExceptio.class)
    public ResponseEntity<Void> IdClienteException(IdDeClienteNoValidoExceptio ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(ClienteNoExisteException.class)
    public ResponseEntity<Void> ClienteNoExisteException(ClienteNoExisteException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
