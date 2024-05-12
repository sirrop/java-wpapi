package com.studrime.wpapi;

import java.util.Map;
import java.util.StringJoiner;

public interface RequestArgs {
    RequestArgs EMPTY = new RequestArgs() {
        @Override
        public String toJson() {
            return "";
        }

        @Override
        public String toSearchParams() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    static RequestArgs fromMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return EMPTY;
        }

        return new RequestArgs() {
            @Override
            public String toJson() {
                StringJoiner joiner = new StringJoiner(",");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String valueString = toJsonString(entry.getValue());
                    joiner.add("\"%s\": %s".formatted(entry.getKey(), valueString));
                }
                return "{" + joiner + "}";
            }

            private String toJsonString(Object value) {
                if (value == null) {
                    return "null";
                } else if (value instanceof String) {
                    return "\"%s\"".formatted(value);
                } else {
                    return value.toString();
                }
            }

            @Override
            public String toSearchParams() {
                StringJoiner joiner = new StringJoiner("&");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value.getClass().isArray()) {
                        Object[] array = (Object[]) value;
                        for (Object v: array) {
                            joiner.add("%s[]=%s".formatted(key, v));
                        }
                    } else {
                        joiner.add("%s=%s".formatted(key, value));
                    }
                }
                return joiner.toString();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }
        };
    }

    String toJson();
    String toSearchParams();
    boolean isEmpty();
}
