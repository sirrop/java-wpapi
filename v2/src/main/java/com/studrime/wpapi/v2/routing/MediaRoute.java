package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.DeletableRoute;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Media;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


public class MediaRoute extends RouteBase implements GettableRoute<Media>, PostableRoute<Void>, DeletableRoute<Void> {
    public MediaRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Media>> get(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(createUriWithSearchParams(args)).GET().build();
        return sendAsync(request, BodyHandlers.jsonSubscriber(Media.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(getUri()).POST(HttpRequest.BodyPublishers.ofString(args.toJson())).build();
        return sendAsync(request, BodyHandlers.VOID);
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> delete(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(getUri())
                .method("DELETE", HttpRequest.BodyPublishers.ofString(args.toJson()))
                .build();
        return sendAsync(request, BodyHandlers.VOID);
    }
}
