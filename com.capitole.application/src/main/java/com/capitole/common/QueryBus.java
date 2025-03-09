package com.capitole.common;

public interface QueryBus {
    <TResponse> TResponse send(Query<TResponse> query);
}
