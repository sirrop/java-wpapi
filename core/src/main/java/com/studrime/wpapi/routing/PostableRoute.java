package com.studrime.wpapi.routing;

import com.studrime.wpapi.RequestArgs;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface PostableRoute<T> extends Route {
    CompletableFuture<HttpResponse<T>> post(RequestArgs args);
    default CompletableFuture<HttpResponse<T>> post() {
        return post(RequestArgs.EMPTY);
    }
}
