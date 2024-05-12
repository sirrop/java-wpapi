package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public record User(
    Links _links,
    @SerializedName("avatar_urls") AvatarUrls avatarUrls,
    Map<String, Boolean> capabilities,
    String description,
    String email,
    @SerializedName("first_name") String firstName,
    long id,
    @SerializedName("last_name") String lastName,
    String link,
    String name,
    String nickname,
    @SerializedName("registered_date") String registeredDate,
    List<String> roles,
    String slug,
    String url) {
}
