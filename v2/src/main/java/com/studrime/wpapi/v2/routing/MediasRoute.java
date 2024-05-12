package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Media;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MediasRoute extends RouteBase implements GettableRoute<List<Media>>, PostableRoute<Void> {
    public MediasRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        return sendPostRequestAsync(args, BodyHandlers.VOID);
    }

    @Override
    public CompletableFuture<HttpResponse<List<Media>>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.list(Media.class));
    }
}
