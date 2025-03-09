package com.capitole.exception;

import java.time.LocalDateTime;

public final class StartDateAfterEndDateException extends DomainException {

    public StartDateAfterEndDateException(LocalDateTime startDate, LocalDateTime endDate) {
        super("The start date " + startDate.toString() + ", must be before than end date " + endDate.toString());
    }
}
