package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public record Post(
        String author,
        Content content,
        String status,
        Links _links,
        String modified,
        Guid guid,
        @SerializedName("featured_media") long featuredMedia,
        boolean sticky,
        String password,
        String format,
        Map<String, Object> meta,
        String link,
        @SerializedName("ping_status") String pingStatus,
        Excerpt excerpt,
        @SerializedName("modified_gmt") String modifiedGmt,
        long id,
        Title title,
        @SerializedName("comment_status") String commentStatus,
        String type,
        String slug,
        String date,
        @SerializedName("date_gmt") String dateGmt,
        long[] categories,
        long[] tags) {
}
