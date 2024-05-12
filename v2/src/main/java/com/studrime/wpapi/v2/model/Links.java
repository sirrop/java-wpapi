package com.studrime.wpapi.v2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record Links(
        List<Author> author,
        List<Collection> collection,
        @SerializedName("https://api.w.org/attachment") List<HttpsApiWOrgAttachment> httpsApiWOrgAttachment,
        @SerializedName("https://api.w.org/meta") List<HttpsApiWOrgMetum> httpsApiWOrgMeta,
        List<Reply> replies,
        List<Self> self,
        @SerializedName("version-history") List<VersionHistory> versionHistory
) {}
