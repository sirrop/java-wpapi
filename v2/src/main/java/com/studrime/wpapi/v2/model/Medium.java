package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record Medium(
        int width,
        int height,
        String file,
        Sizes sizes,
        @SerializedName("image_meta") Map<String, Object> imageMeta
) {}
