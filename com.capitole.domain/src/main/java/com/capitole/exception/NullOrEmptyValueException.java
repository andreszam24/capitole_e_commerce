package com.capitole.exception;

public final class NullOrEmptyValueException extends DomainException{

    public NullOrEmptyValueException(String fieldName) {
        super("The field "+ fieldName +" cannot be empty or null");
    }
}
