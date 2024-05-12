package com.studrime.wpapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

final class NamespaceLoader {
    private final ServiceLoader<Namespace> loader = ServiceLoader.load(Namespace.class);
    private final Map<String, Namespace> namespaces = new HashMap<>();

    private boolean cache = true;

    public void setCache(boolean enabled) {
        this.cache = enabled;
        if (!cache) clearCache();
    }

    public void clearCache() {
        namespaces.clear();
    }

    public Optional<Namespace> getNamespace(String namespace) {
        if (cache) {
            return getNamespaceWithCache(namespace);
        } else {
            return getNamespaceWithoutCache(namespace);
        }
    }

    public Optional<Namespace> getNamespaceWithoutCache(String namespace) {
        return loader.stream()
                .filter(provider -> provider.type().getAnnotation(NamespaceBinding.class).value().equals(namespace))
                .findFirst()
                .map(ServiceLoader.Provider::get);
    }

    public Optional<Namespace> getNamespaceWithCache(String namespace) {
        if (!cache) throw new UnsupportedOperationException("cache disabled");
        return Optional.ofNullable(namespaces.computeIfAbsent(namespace, (ns) -> getNamespaceWithoutCache(ns).orElse(null)));
    }
}
