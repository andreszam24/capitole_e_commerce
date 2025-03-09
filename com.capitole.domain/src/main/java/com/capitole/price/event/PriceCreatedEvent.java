package com.capitole.price.event;

import com.capitole.common.DomainEvent;
import com.capitole.price.Price;

public final class PriceCreatedEvent extends DomainEvent {

    private final Price price;

    public PriceCreatedEvent(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }
}
