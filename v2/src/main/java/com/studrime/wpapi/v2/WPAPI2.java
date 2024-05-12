package com.studrime.wpapi.v2;

import com.studrime.wpapi.Namespace;
import com.studrime.wpapi.NamespaceBinding;
import com.studrime.wpapi.routing.NamedArg;
import com.studrime.wpapi.routing.Routing;
import com.studrime.wpapi.v2.routing.*;

import java.net.http.HttpClient;

@NamespaceBinding("wp/v2")
public final class WPAPI2 implements Namespace {
    private String apiRoot;
    private HttpClient client;


    @Override
    public void setApiRoot(String apiRoot) {
        this.apiRoot = apiRoot;
    }

    @Override
    public String getApiRoot() {
        return apiRoot;
    }

    @Override
    public void setHttpClient(HttpClient client) {
        this.client = client;
    }

    @Routing("posts")
    public PostsRoute posts() {
        return new PostsRoute(createEndpoint("posts"), client);
    }

    @Routing("posts/(?<id>[\\d]+)")
    public PostRoute posts(@NamedArg("id") int id) {
        return new PostRoute(createEndpoint("posts/" + id), client);
    }

    @Routing("posts/(?<parentId>[\\d]+)/meta")
    public PostMetasRoute postMeta(@NamedArg("parentId") int parentId) {
        return new PostMetasRoute(createEndpoint("posts/" + parentId + "/meta"), client);
    }

    @Routing("posts/(?<parentId>[\\d]+)/meta/(?<id>[\\d]+)")
    public PostMetaRoute postMeta(@NamedArg("parentId") int parentId, @NamedArg("id") int id) {
        return new PostMetaRoute(createEndpoint("posts/" + parentId + "/meta/" + id), client);
    }

    @Routing("posts/(?<parentId>[\\d]+)/revisions")
    public PostRevisionsRoute postRevisions(@NamedArg("parentId") int parentId) {
        return new PostRevisionsRoute(createEndpoint("posts/" + parentId + "/revisions"), client);
    }

    @Routing("posts/(?<parentId>[\\d]+)/revisions/(?<id>[\\d]+)")
    public PostRevisionRoute postRevisions(@NamedArg("parentId") int parentId, @NamedArg("id") int id) {
        return new PostRevisionRoute(createEndpoint("posts/" + parentId + "/revisions/" + id), client);
    }

    @Routing("pages")
    public PagesRoute pages() {
        return new PagesRoute(createEndpoint("pages"), client);
    }

    @Routing("pages/(?<id>[\\d]+)")
    public PageRoute pages(@NamedArg("id") int id) {
        return new PageRoute(createEndpoint("pages/" + id), client);
    }

    @Routing("media")
    public MediasRoute media() {
        return new MediasRoute(createEndpoint("media"), client);
    }

    @Routing("media/(?<id>[\\d]+)")
    public MediaRoute media(@NamedArg("id") int id) {
        return new MediaRoute(createEndpoint("media/" + id), client);
    }

    @Routing("types")
    public PostTypesRoute types() {
        return new PostTypesRoute(createEndpoint("types"), client);
    }

    @Routing("types/(?<type>[.]+)")
    public PostTypeRoute types(@NamedArg("type") String type) {
        return new PostTypeRoute(createEndpoint("types/" + type), client);
    }

    @Routing("statuses")
    public StatusesRoute statuses() {
        return new StatusesRoute(createEndpoint("statuses"), client);
    }

    @Routing("statuses/(?<status>[.]+)")
    public StatusRoute statuses(@NamedArg("status") String status) {
        return new StatusRoute(createEndpoint("statuses/" + status), client);
    }

    @Routing("comments")
    public CommentsRoute comments() {
        return new CommentsRoute(createEndpoint("comments"), client);
    }

    @Routing("comments/(?<id>[\\d]+)")
    public CommentRoute comments(@NamedArg("id") int id) {
        return new CommentRoute(createEndpoint("comments/" + id), client);
    }

    @Routing("taxonomies")
    public TaxonomiesRoute taxonomies() {
        return new TaxonomiesRoute(createEndpoint("taxonomies"), client);
    }

    @Routing("taxonomies/(?<taxonomy>[.]+)")
    public TaxonomyRoute taxonomies(@NamedArg("taxonomy") String taxonomy) {
        return new TaxonomyRoute(createEndpoint("taxonomies/" + taxonomy), client);
    }

    @Routing("categories")
    public CategoriesRoute categories() {
        return new CategoriesRoute(createEndpoint("categories"), client);
    }

    @Routing("categories/(?<id>[\\d]+)")
    public CategoryRoute categories(@NamedArg("id") int id) {
        return new CategoryRoute(createEndpoint("categories/" + id), client);
    }

    @Routing("tags")
    public TagsRoute tags() {
        return new TagsRoute(createEndpoint("tags"), client);
    }

    @Routing("tags/(?<id>[\\d]+)")
    public TagRoute tags(@NamedArg("id") int id) {
        return new TagRoute(createEndpoint("tags/" + id), client);
    }

    @Routing("users")
    public UsersRoute users() {
        return new UsersRoute(createEndpoint("users"), client);
    }

    @Routing("users/(?<id>[.]+)")
    public UserRoute users(@NamedArg("id") int id) {
        return new UserRoute(createEndpoint("users/" + id), client);
    }

    @Override
    public String toString() {
        return "WPAPI2 { \"apiRoot\": %s }".formatted(apiRoot == null ? "null" : "\"" + apiRoot + "\"");
    }
}
