package com.studrime.wpapi.v2.model;

import java.util.List;
import java.util.Map;

public record Taxonomy(
        String slug,
        String description,
        String name,
        List<String> types,
        boolean hierarchical,
        Map<String, String> labels
) {}
