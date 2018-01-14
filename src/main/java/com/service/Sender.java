package com.service;

import java.util.function.Consumer;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
public abstract class Sender<T, R> {

    public abstract R send(T t);

    public void send(T t, Consumer<R> success, Consumer<Throwable> failure) {
        try {
            R r = send(t);
            success.accept(r);
        } catch (Exception e) {
            failure.accept(e);
        }
    }
}
