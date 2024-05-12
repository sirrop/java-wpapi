package com.studrime.wpapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public final class WordPressApi {
    private final String apiRoot;
    private final boolean enabledPrettyPermalinks;
    private final Map<String, Namespace> namespaceHandlers = new HashMap<>();
    private JsonObject apiRootResponse;
    private final DefaultHttpClient client = new DefaultHttpClient();

    private WordPressApi(String apiRoot, boolean enabledPrettyPermalinks) {
        this.apiRoot = apiRoot;
        this.enabledPrettyPermalinks = enabledPrettyPermalinks;
    }

    public static CompletableFuture<WordPressApi> discover(String root) {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder(URI.create(root))
                    .HEAD()
                    .build();
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        String link = response.headers()
                                .firstValue("Link")
                                .map(linkString -> {
                                    int index = linkString.indexOf(">");
                                    return linkString.substring(1, index);
                                })
                                .orElseThrow();
                        boolean enabledPrettyPermalinks = link.contains("wp-json");
                        return new WordPressApi(link, enabledPrettyPermalinks);
                    })
                    .thenApplyAsync(WordPressApi::initApiRoot)
                    .thenApplyAsync(WordPressApi::registerNamespaces);
        }
    }

    private WordPressApi initApiRoot() {
        HttpResponse<String> response;
        try(HttpClient client = HttpClient.newHttpClient()) {
            response = client.send(HttpRequest.newBuilder(URI.create(this.apiRoot)).build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();

        Gson gson = new Gson();
        apiRootResponse = gson.fromJson(json, JsonObject.class);

        return this;
    }

    private void checkApiRootInitialized() {
        if (apiRootResponse == null) {
            throw new IllegalStateException("Api root not initialized");
        }
    }

    private WordPressApi registerNamespaces() {
        checkApiRootInitialized();

        NamespaceLoader loader = new NamespaceLoader();
        List<JsonElement> value = apiRootResponse.get("namespaces").getAsJsonArray().asList();

        for (JsonElement jsonElement : value) {
            String namespace = jsonElement.getAsString();
            loader.getNamespace(namespace)
                            .ifPresent(ns -> {
                                ns.setApiRoot(getApiRootPath());
                                ns.setHttpClient(client);
                                namespaceHandlers.put(namespace, ns);
                            });
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <N extends Namespace> N namespace(String namespace) {
        return (N) namespaceHandlers.get(namespace);
    }

    public String getApiRootPath() {
        return apiRoot;
    }

    public boolean isEnabledPrettyPermalinks() {
        return enabledPrettyPermalinks;
    }

    public Set<String> getNamespaces() {
        return Collections.unmodifiableSet(namespaceHandlers.keySet());
    }

    public WordPressApi use(Middleware middleware) {
        client.use(middleware);
        return this;
    }
}
