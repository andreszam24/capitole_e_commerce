package com.capitole.price;

import com.capitole.common.ValueObject;
import com.capitole.exception.NegativeOrNullNumberException;
import com.capitole.exception.NullOrEmptyValueException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@ValueObject
public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money create(BigDecimal amount, Currency currency) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeOrNullNumberException("price");
        }

        if (currency == null) {
            throw new NullOrEmptyValueException("currency");
        }

        return new Money(amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
