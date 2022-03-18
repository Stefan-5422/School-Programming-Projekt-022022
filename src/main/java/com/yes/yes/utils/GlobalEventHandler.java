package com.yes.yes.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class GlobalEventHandler {
    private GlobalEventHandler() {
    }

    private static final EventHandler handler = new EventHandler();

    public static <T> void addListener(String eventName, Consumer<T> function) {
        handler.addListener(eventName, function);
    }

    public static void removeListener(String eventName, Function<Object, Void> function) {
        handler.removeListener(eventName, function);
    }

    public static void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
