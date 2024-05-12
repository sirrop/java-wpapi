package com.studrime.wpapi.routing;

import com.studrime.wpapi.RequestArgs;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface DeletableRoute<T> extends Route {
    CompletableFuture<HttpResponse<T>> delete(RequestArgs args);
    default CompletableFuture<HttpResponse<T>> delete() {
        return delete(RequestArgs.EMPTY);
    }
}
