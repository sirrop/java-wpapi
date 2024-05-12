package com.studrime.wpapi.auth.basic;

import com.studrime.wpapi.Middleware;
import com.studrime.wpapi.ThrowableFunction;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record BasicAuthMiddleware(String username, String password) implements Middleware {
    public String encode() {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public <T> HttpResponse<T> handle(HttpRequest request, ThrowableFunction<HttpRequest, HttpResponse<T>, ?> next) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(request, (name, value) -> !name.equals("Authorization"));
        requestBuilder.header("Authorization", "Basic " + encode());
        return next.apply(requestBuilder.build());
    }
}
