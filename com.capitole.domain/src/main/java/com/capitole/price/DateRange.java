package com.capitole.price;

import com.capitole.common.ValueObject;
import com.capitole.exception.NullOrEmptyValueException;
import com.capitole.exception.StartDateAfterEndDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@ValueObject
public final class DateRange {
    private static final String DATE_PATTERN = "YYYY-MM-DD HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private DateRange(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange create(LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null || endDate == null ) {
            throw new NullOrEmptyValueException("start date or end date");
        }

        if (!startDate.isBefore(endDate)) {
            throw new StartDateAfterEndDateException(startDate, endDate);
        }
        return new DateRange(startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(startDate, dateRange.startDate) && Objects.equals(endDate, dateRange.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    public String formatStartDate() {
        return this.startDate.format(FORMATTER);
    }

    public String formatEndDate() {
        return this.endDate.format(FORMATTER);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
