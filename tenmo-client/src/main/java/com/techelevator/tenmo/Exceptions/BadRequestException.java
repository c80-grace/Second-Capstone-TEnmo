package com.techelevator.tenmo.Exceptions;

public class BadRequestException extends RuntimeException {
    private final ValidationError[] errors;

    public BadRequestException(String message, ValidationError[] errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationError[] getErrors() {
        return errors;
    }
}
