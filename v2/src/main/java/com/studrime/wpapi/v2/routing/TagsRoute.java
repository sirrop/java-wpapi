package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Term;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TagsRoute extends RouteBase implements GettableRoute<List<Term>>, PostableRoute<Void> {

    public TagsRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<List<Term>>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.list(Term.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        return sendPostRequestAsync(args, BodyHandlers.VOID);
    }
}
