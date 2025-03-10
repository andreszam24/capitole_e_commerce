package com.capitole.common;

public interface QueryBus {
    <TResponse> TResponse execute(Query<TResponse> query);
}
