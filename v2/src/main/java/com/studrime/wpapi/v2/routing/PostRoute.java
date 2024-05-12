package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.DeletableRoute;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Post;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PostRoute extends RouteBase implements GettableRoute<Post>, PostableRoute<Void>, DeletableRoute<Void> {
    public PostRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Post>> get(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(createUriWithSearchParams(args)).build();
        return sendAsync(request, BodyHandlers.jsonSubscriber(Post.class));
    }

    public CompletableFuture<HttpResponse<Post>> get(String context) {
        return get(RequestArgs.fromMap(Map.of("context", context)));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> delete(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(getUri()).method("DELETE", HttpRequest.BodyPublishers.ofString(args.toJson())).build();
        return sendAsync(request, BodyHandlers.VOID);
    }

    public CompletableFuture<HttpResponse<Void>> delete(boolean force) {
        return delete(RequestArgs.fromMap(Map.of("force", force)));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(getUri())
                .POST(HttpRequest.BodyPublishers.ofString(args.toJson()))
                .build();
        return sendAsync(request, BodyHandlers.VOID);
    }
}
