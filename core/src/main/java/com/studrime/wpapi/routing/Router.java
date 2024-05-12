package com.studrime.wpapi.routing;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Optional;

public final class Router {
    private final Table<String, String, HttpResponse.BodyHandler<?>> routes = Tables.newCustomTable(new HashMap<>(), HashMap::new);

    public Router get(String path, HttpResponse.BodyHandler<?> bodyHandler) {
        routes.put(path, "GET", bodyHandler);
        return this;
    }

    public Router post(String path, HttpResponse.BodyHandler<?> bodyHandler) {
        routes.put(path, "POST", bodyHandler);
        return this;
    }

    public Router put(String path, HttpResponse.BodyHandler<?> bodyHandler) {
        routes.put(path, "PUT", bodyHandler);
        return this;
    }

    public Router delete(String path, HttpResponse.BodyHandler<?> bodyHandler) {
        routes.put(path, "DELETE", bodyHandler);
        return this;
    }

    @SuppressWarnings("unchecked")
    <T> Optional<HttpResponse.BodyHandler<T>> getHandler(String path, String method) {
        for (Table.Cell<String, String, HttpResponse.BodyHandler<?>> cell : routes.cellSet()) {
            if ( path.matches(cell.getRowKey()) && cell.getColumnKey().equals(method) ) {
                return Optional.of((HttpResponse.BodyHandler<T>) cell.getValue());
            }
        }
        return Optional.empty();
    }
}
