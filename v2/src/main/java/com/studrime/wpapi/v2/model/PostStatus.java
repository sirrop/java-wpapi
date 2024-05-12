package com.studrime.wpapi.v2.model;

public enum PostStatus {
    DRAFT,
    PUBLISH,
    PENDING,
    FUTURE,
    PRIVATE;

    public final String value;

    PostStatus() {
        this.value = this.name().toLowerCase();
    }
}
