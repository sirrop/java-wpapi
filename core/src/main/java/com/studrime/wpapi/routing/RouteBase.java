package com.studrime.wpapi.routing;

import com.studrime.wpapi.HttpMethod;
import com.studrime.wpapi.RequestArgs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class RouteBase implements Route {
    private final String path;
    private final HttpClient client;

    protected RouteBase(String path, HttpClient client) {
        this.path = Objects.requireNonNull(path);
        this.client = Objects.requireNonNull(client);
    }

    public String getPath() {
        return path;
    }

    protected URI getUri() {
        return URI.create(path);
    }

    protected URI createUriWithSearchParams(RequestArgs args) {
        if (args.isEmpty()) return URI.create(getPath());

        String delimiter = isEnabledPrettyPermalinks() ? "&" : "?";
        return URI.create(getPath() + delimiter + args.toSearchParams());
    }

    protected HttpRequest createGetRequest(RequestArgs args) {
        return HttpRequest.newBuilder(createUriWithSearchParams(args)).build();
    }

    protected HttpRequest createPostRequest(RequestArgs args) {
        return HttpRequest.newBuilder(getUri()).POST(HttpRequest.BodyPublishers.ofString(args.toJson())).build();
    }

    protected HttpRequest createDeleteRequest(RequestArgs args) {
        return HttpRequest.newBuilder(getUri()).method("DELETE", HttpRequest.BodyPublishers.ofString(args.toJson())).build();
    }

    public boolean isEnabledPrettyPermalinks() {
        return path.contains("?rest_root=");
    }

    @Override
    public <T>CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> bodyHandler) {
        String method = request.method().toUpperCase(Locale.ENGLISH);
        if ( !supports(HttpMethod.valueOf(method)) ) throw new UnsupportedOperationException("%s method is not supported".formatted(method));
        return client.sendAsync(request, bodyHandler);
    }

    protected <T>CompletableFuture<HttpResponse<T>> sendGetRequestAsync(RequestArgs args, HttpResponse.BodyHandler<T> bodyHandler) {
        return sendAsync(createGetRequest(args), bodyHandler);
    }

    protected <T>CompletableFuture<HttpResponse<T>> sendPostRequestAsync(RequestArgs args, HttpResponse.BodyHandler<T> bodyHandler) {
        return sendAsync(createPostRequest(args), bodyHandler);
    }

    protected <T>CompletableFuture<HttpResponse<T>> sendDeleteRequestAsync(RequestArgs args, HttpResponse.BodyHandler<T> bodyHandler) {
        return sendAsync(createDeleteRequest(args), bodyHandler);
    }
}
