package com.capitole.exception;

public final class NegativeOrNullNumberException extends DomainException{
    public NegativeOrNullNumberException(String fieldName) {
        super("The field "+ fieldName +" cannot be negative or null");
    }

}
