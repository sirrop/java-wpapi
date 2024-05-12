package com.studrime.wpapi.v2.routing;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class BodyHandlers {
    private BodyHandlers() {}

    public static final HttpResponse.BodyHandler<Void> VOID = (info) -> null;

    public static <T>HttpResponse.BodyHandler<T> jsonSubscriber(Class<T> clazz) {
        return info -> HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8),
                json -> {
                    Gson gson = new Gson();
                    return gson.fromJson(json, clazz);
                }
        );
    }

    @SuppressWarnings("unchecked")
    public static <T>HttpResponse.BodyHandler<List<T>> list(Class<T> clazz) {
        return info -> HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8),
                json -> {
                    Gson gson = new Gson();
                    return Arrays.asList(gson.fromJson(json, (Class<T[]>) clazz.arrayType()));
                }
        );
    }
}
