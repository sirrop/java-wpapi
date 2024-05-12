package com.studrime.wpapi.v2.model;

public record DeleteResponse<T>(boolean deleted, T previous) {}
