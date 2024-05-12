package com.studrime.wpapi.routing;

import com.studrime.wpapi.HttpMethod;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface Route {
    /**
     * 対応しているHTTP MethodのSetを返します。Methodは
     * @return 対応しているHTTP MethodのSet
     */
    default Set<HttpMethod> getSupportedMethods() {
        Set<HttpMethod> supportedMethods = new HashSet<>();
        if (this instanceof GettableRoute) supportedMethods.add(HttpMethod.GET);
        if (this instanceof PostableRoute) supportedMethods.add(HttpMethod.POST);
        if (this instanceof DeletableRoute) supportedMethods.add(HttpMethod.DELETE);
        return Collections.unmodifiableSet(supportedMethods);
    }
    default boolean supports(HttpMethod method) {
        return getSupportedMethods().contains(method);
    }
    <T>CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> handler);
}
