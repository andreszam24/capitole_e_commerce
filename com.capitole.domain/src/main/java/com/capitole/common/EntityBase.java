package com.capitole.common;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityBase {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
