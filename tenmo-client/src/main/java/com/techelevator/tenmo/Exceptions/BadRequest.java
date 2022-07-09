package com.techelevator.tenmo.Exceptions;

public class BadRequest {
    private ValidationError[] errors;

    public ValidationError[] getErrors() {
        return errors;
    }

    public void setErrors(ValidationError[] errors) {
        this.errors = errors;
    }
}

