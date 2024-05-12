package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Page;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PagesRoute extends RouteBase implements GettableRoute<List<Page>>, PostableRoute<Void> {
    public PagesRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<List<Page>>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.list(Page.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        return sendPostRequestAsync(args, BodyHandlers.VOID);
    }
}
