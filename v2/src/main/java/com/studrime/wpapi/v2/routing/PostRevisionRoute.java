package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.DeletableRoute;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Revision;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class PostRevisionRoute extends RouteBase implements GettableRoute<Revision>, PostableRoute<Void>, DeletableRoute<Void> {
    public PostRevisionRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> delete(RequestArgs args) {
        return sendDeleteRequestAsync(args, BodyHandlers.VOID);
    }

    @Override
    public CompletableFuture<HttpResponse<Revision>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.jsonSubscriber(Revision.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        return sendPostRequestAsync(args, BodyHandlers.VOID);
    }
}
