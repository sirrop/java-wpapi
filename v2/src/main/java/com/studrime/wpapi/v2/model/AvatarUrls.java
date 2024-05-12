package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

public record AvatarUrls(
        @SerializedName("24") String _24,
        @SerializedName("48") String _48,
        @SerializedName("96") String _96
) {}
