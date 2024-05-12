package com.studrime.wpapi.v2.model;


import com.google.gson.annotations.SerializedName;

public record Thumbnail(
        String file,
        int width,
        int height,
        @SerializedName("mime-type") String mimeType,
        @SerializedName("source_url") String sourceUrl
) {
}
