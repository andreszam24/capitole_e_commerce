package com.capitole.common;

import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QueryBusImpl implements QueryBus {

    private Map<Class, QueryHandler> handlers;

    public QueryBusImpl(List<QueryHandler> queryHandlers) {
        this.handlers = new HashMap<>();
        queryHandlers.forEach(queryHandler -> {
            Class<?> queryClass = getQueryClass(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    public <T> T execute(Query<T> query) {
        if (!handlers.containsKey(query.getClass())) {
            throw new RuntimeException(String.format("No handler for %s", query.getClass().getName()));
        }
        return (T) handlers.get(query.getClass()).handle(query);
    }

    private Class<?> getQueryClass(QueryHandler handler) {
        Type queryInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return getClass(queryInterface.getTypeName());
    }

    private Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }
}
