package com.capitole.common;

import java.util.HashMap;
import java.util.Map;

public final class SimpleQueryBus implements QueryBus {

    private final Map<Class<? extends Query<?>>, QueryHandler<?, ?>> handlers = new HashMap<>();

    public <TQuery extends Query<TResponse>, TResponse> void registerHandler(
            Class<TQuery> queryType, QueryHandler<TQuery, TResponse> handler) {
        handlers.put(queryType, handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <TResponse> TResponse send(Query<TResponse> query) {

        QueryHandler<Query<TResponse>, TResponse> handler =
                (QueryHandler<Query<TResponse>, TResponse>) handlers.get(query.getClass());
        if (handler == null) {
            throw new RuntimeException("There is not a handler to the query : " + query.getClass());
        }
        return handler.handle(query);
    }
}
