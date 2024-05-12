package com.studrime.wpapi;

import com.studrime.wpapi.routing.NamedArg;
import com.studrime.wpapi.routing.Route;
import com.studrime.wpapi.routing.Routing;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Namespace {
    void setApiRoot(String apiRoot);
    String getApiRoot();
    void setHttpClient(HttpClient client);

    default String createEndpoint(String path) {
        return getApiRoot() + getNamespace() + "/" + path;
    }

    default String getNamespace() {
        return this.getClass().getAnnotation(NamespaceBinding.class).value();
    }

    default Route route(String route) {
        var self = this;
        return (Route) Arrays.stream(this.getClass().getMethods())
                .filter(method -> {
                    Routing routing = method.getAnnotation(Routing.class);
                    return routing != null && route.matches(routing.value());
                })
                .findFirst()
                .map(method -> {
                    Pattern pattern = Pattern.compile(method.getAnnotation(Routing.class).value());
                    Matcher matcher = pattern.matcher(route);

                    if (matcher.find()) {
                        String[] args = new String[method.getParameterCount()];
                        int pos = 0;
                        for (Annotation[] annotations : method.getParameterAnnotations()) {
                            for (Annotation annotation : annotations) {
                                if (annotation instanceof NamedArg namedArg) {
                                    args[pos++] = matcher.group(namedArg.value());
                                }
                            }
                        }

                        Object[] typedArgs = new Object[args.length];
                        pos = 0;
                        for (Class<?> argType: method.getParameterTypes()) {
                            String arg = args[pos];
                            if (argType.isAssignableFrom(String.class)) {
                                typedArgs[pos++] = matcher.group(arg);
                            } else if (argType.isAssignableFrom(Long.class)) {
                                typedArgs[pos++] = Long.getLong(matcher.group(arg));
                            } else if (argType.isAssignableFrom(Integer.class)) {
                                typedArgs[pos++] = Integer.getInteger(matcher.group(arg));
                            } else if (argType.isAssignableFrom(Double.class)) {
                                typedArgs[pos++] = Double.valueOf(matcher.group(arg));
                            } else if (argType.isAssignableFrom(Boolean.class)) {
                                typedArgs[pos++] = Boolean.valueOf(matcher.group(arg));
                            } else if (argType.isAssignableFrom(Float.class)) {
                                typedArgs[pos++] = Float.valueOf(matcher.group(arg));
                            } else if (argType.isAssignableFrom(Byte.class)) {
                                typedArgs[pos++] = Byte.valueOf(matcher.group(arg));
                            } else {
                                throw new RuntimeException("Unsupported argument type: " + argType);
                            }
                        }
                        method.setAccessible(true);
                        try {
                            return method.invoke(self, typedArgs);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        throw new Error("Not reachable");
                    }
                }).orElseThrow();
    }
}
