package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.DeletableRoute;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Comment;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class CommentRoute extends RouteBase implements GettableRoute<Comment>, PostableRoute<Void>, DeletableRoute<Void> {
    public CommentRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> delete(RequestArgs args) {
        return sendDeleteRequestAsync(args, BodyHandlers.VOID);
    }

    @Override
    public CompletableFuture<HttpResponse<Comment>> get(RequestArgs args) {
        return sendGetRequestAsync(args, BodyHandlers.jsonSubscriber(Comment.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        return sendPostRequestAsync(args, BodyHandlers.VOID);
    }
}
