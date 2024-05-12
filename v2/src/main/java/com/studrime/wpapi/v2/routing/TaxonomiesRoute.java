package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Taxonomy;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class TaxonomiesRoute extends RouteBase implements GettableRoute<Taxonomy> {
    public TaxonomiesRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Taxonomy>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.jsonSubscriber(Taxonomy.class));
    }
}
