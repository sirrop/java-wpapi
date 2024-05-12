package com.studrime.wpapi.routing;

import com.studrime.wpapi.RequestArgs;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface GettableRoute<T> extends Route {
    CompletableFuture<HttpResponse<T>> get(RequestArgs args);
    default CompletableFuture<HttpResponse<T>> get() {
        return get(RequestArgs.EMPTY);
    }
}
