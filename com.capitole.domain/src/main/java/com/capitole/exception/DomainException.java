package com.capitole.exception;

public sealed class DomainException extends RuntimeException permits NegativeOrNullNumberException, NullOrEmptyValueException, StartDateAfterEndDateException {

    public DomainException(String message) { super(message); }
}