package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Status;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StatusesRoute extends RouteBase implements GettableRoute<List<Status>> {
    public StatusesRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<List<Status>>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.list(Status.class));
    }
}
