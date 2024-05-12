package com.studrime.wpapi;

@FunctionalInterface
public interface ThrowableFunction<T,R,E extends Exception> {
    R apply(T t) throws E;
}
