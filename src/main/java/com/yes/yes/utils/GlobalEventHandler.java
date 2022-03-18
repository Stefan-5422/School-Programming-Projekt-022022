package com.yes.yes.utils;

import java.util.function.Function;

public abstract class GlobalEventHandler {
    private static final EventHandler handler = new EventHandler();

    public static void addListener(String eventName, Function<Object, Void> function) {
        handler.addListener(eventName, function);
    }

    public static void removeListener(String eventName, Function<Object, Void> function) {
        handler.removeListener(eventName, function);
    }

    public static void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
