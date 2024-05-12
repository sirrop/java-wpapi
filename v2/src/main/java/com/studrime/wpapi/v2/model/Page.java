package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

public record Page (
    Links _links,
    long author,
    @SerializedName("comment_status") String commentStatus,
    Content content,
    String date,
    @SerializedName("date_gmt") String dateGmt,
    Excerpt excerpt,
    @SerializedName("featured_media") long featuredMedia,
    Guid guid,
    long id,
    String link,
    @SerializedName("menu_order") long menuOrder,
    String modified,
    @SerializedName("modified_gmt") String modifiedGmt,
    long parent,
    String password,
    @SerializedName("ping_status")
    String pingStatus,
    String slug,
    String status,
    String template,
    Title title,
    String type) {
}
