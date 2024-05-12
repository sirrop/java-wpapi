package com.studrime.wpapi.v2.routing;

import com.studrime.wpapi.RequestArgs;
import com.studrime.wpapi.routing.GettableRoute;
import com.studrime.wpapi.routing.PostableRoute;
import com.studrime.wpapi.routing.RouteBase;
import com.studrime.wpapi.v2.model.Post;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PostsRoute extends RouteBase implements GettableRoute<List<Post>>, PostableRoute<Void> {
    public PostsRoute(String path, HttpClient client) {
        super(path, client);
    }

    @Override
    public CompletableFuture<HttpResponse<List<Post>>> get(RequestArgs args) {
        System.out.printf("Call PostsRoute::get(%s)%n", args.toJson());
        return sendGetRequestAsync(args, BodyHandlers.list(Post.class));
    }

    @Override
    public CompletableFuture<HttpResponse<Void>> post(RequestArgs args) {
        HttpRequest request = HttpRequest.newBuilder(getUri()).POST(HttpRequest.BodyPublishers.ofString(args.toJson())).build();
        return sendAsync(request, (info) -> {
            if (info.statusCode() != 200) throw new RuntimeException();
            return null;
        });
    }
}
