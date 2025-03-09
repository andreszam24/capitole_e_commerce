package com.capitole.price;

import com.capitole.common.ValueObject;
import com.capitole.exception.NegativeOrNullNumberException;

@ValueObject
public final class PriceList {

    private final int id;

    private PriceList(int id) {
        this.id = id;
    }

    public static PriceList create(int id) {

        if (id <= 0) {
            throw new NegativeOrNullNumberException("PriceList");
        }
        return new PriceList(id);
    }

    public int getId() {
        return id;
    }
}
