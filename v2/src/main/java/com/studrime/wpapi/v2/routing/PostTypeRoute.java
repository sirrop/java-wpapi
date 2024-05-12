package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.PostType;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class PostTypeRoute extends RouteBase implements GettableRoute<PostType> {
    public PostTypeRoute(String path, HttpClient client) {
        super(path, client);
    }


    @Override
    public CompletableFuture<HttpResponse<PostType>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.jsonSubscriber(PostType.class));
    }
}
