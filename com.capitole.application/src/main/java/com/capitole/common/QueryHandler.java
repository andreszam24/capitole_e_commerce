package com.capitole.common;

    public interface QueryHandler <TQuery extends Query<TResponse>, TResponse> {
    TResponse handle(TQuery query);
}
