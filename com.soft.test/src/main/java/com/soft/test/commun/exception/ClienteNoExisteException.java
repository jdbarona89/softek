package com.soft.test.commun.exception;

public class ClienteNoExisteException extends RuntimeException {
    public ClienteNoExisteException(String message) {
        super(message);
    }
}
