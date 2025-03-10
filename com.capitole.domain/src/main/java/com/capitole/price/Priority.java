package com.capitole.price;

import com.capitole.common.ValueObject;
import com.capitole.exception.NegativeOrNullNumberException;

@ValueObject
public final class Priority {
    private final int value;

    private Priority(int value) {
        this.value = value;
    }

    public static Priority create(int value) {

        if (value < 0) {
            throw new NegativeOrNullNumberException("Priority");
        }
        return new Priority(value);
    }

    public int getValue() {
        return value;
    }
}
