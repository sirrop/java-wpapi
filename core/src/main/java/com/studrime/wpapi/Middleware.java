package com.studrime.wpapi;


import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@FunctionalInterface
public interface Middleware {
    <T>HttpResponse<T> handle(HttpRequest request, ThrowableFunction<HttpRequest, HttpResponse<T>, ?> next) throws Exception;
}
