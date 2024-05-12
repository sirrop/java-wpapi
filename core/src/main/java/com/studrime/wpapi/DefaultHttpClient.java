package com.studrime.wpapi;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

final class DefaultHttpClient extends HttpClient {
    private final HttpClient delegate = HttpClient.newHttpClient();
    private final List<Middleware> middlewares = new ArrayList<>();

    public DefaultHttpClient use(Middleware middleware) {
        this.middlewares.add(middleware);
        return this;
    }

    @Override
    public Optional<CookieHandler> cookieHandler() {
        return delegate.cookieHandler();
    }

    @Override
    public Optional<Duration> connectTimeout() {
        return delegate.connectTimeout();
    }

    @Override
    public Redirect followRedirects() {
        return delegate.followRedirects();
    }

    @Override
    public Optional<ProxySelector> proxy() {
        return delegate.proxy();
    }

    @Override
    public SSLContext sslContext() {
        return delegate.sslContext();
    }

    @Override
    public SSLParameters sslParameters() {
        return delegate.sslParameters();
    }

    @Override
    public Optional<Authenticator> authenticator() {
        return delegate.authenticator();
    }

    @Override
    public Version version() {
        return delegate.version();
    }

    @Override
    public Optional<Executor> executor() {
        return delegate.executor();
    }

    @Override
    public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        ThrowableFunction<HttpRequest, HttpResponse<T>, Exception> next = (req) -> delegate.send(req, responseBodyHandler);

        for (int i = middlewares.size() - 1; i >= 0; i--) {
            Middleware middleware = middlewares.get(i);
            final ThrowableFunction<HttpRequest, HttpResponse<T>, ?> finalNext = next;
            next = (req) -> middleware.handle(req, finalNext);
        }

        try {
            return next.apply(request);
        } catch (IOException|InterruptedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        CompletableFuture<HttpResponse<T>> future = new CompletableFuture<>();
        Supplier<HttpResponse<T>> responseSupplier = () -> {
            try {
                return this.send(request, responseBodyHandler);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Optional<Executor> executor = executor();
        if (executor.isPresent()) {
            return future.completeAsync(responseSupplier, executor.get());
        }
        return future.completeAsync(responseSupplier);
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, HttpResponse.PushPromiseHandler<T> pushPromiseHandler) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
