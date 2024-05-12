package com.studrime.wpapi.v2.model;

public record Term(
        long id,
        int count,
        String description,
        String link,
        String name,
        String slug,
        String taxonomy,
        long parent
) {
}
