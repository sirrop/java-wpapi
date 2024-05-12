package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record Media(
        long id,
        @SerializedName("media_details") Map<String, Object> mediaDetails,
        int author,
        Guid guid,
        String date,
        @SerializedName("date_gmt") String dateGmt,
        String modified,
        @SerializedName("modified_gmt") String modifiedGmt,
        String slug,
        String type,
        String link,
        Title title,
        @SerializedName("comment_status") String commentStatus,
        @SerializedName("ping_status") String pingStatus,
        @SerializedName("alt_text") String altText,
        String caption,
        String description,
        @SerializedName("media_type") String mediaType,
        long post,
        @SerializedName("source_url") String sourceUrl,
        @SerializedName("mime_type") String mimeType
) {}