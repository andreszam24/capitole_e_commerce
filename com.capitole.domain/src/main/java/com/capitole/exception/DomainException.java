package com.capitole.exception;

public sealed class DomainException extends RuntimeException permits NegativeOrNullNumberException, NotFoundException, NullOrEmptyValueException, StartDateAfterEndDateException {

    public DomainException(String message) { super(message); }
}