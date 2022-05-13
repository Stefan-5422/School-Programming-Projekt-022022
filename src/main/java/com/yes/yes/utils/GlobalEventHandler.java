package com.yes.yes.utils;

import java.util.function.Consumer;

public class GlobalEventHandler {
    private static final EventHandler handler = new EventHandler();

    private GlobalEventHandler() {
    }

    public static <T> void addListener(String eventName, Object object, Consumer<T> function) {
        handler.addListener(eventName, object, function);
    }

    public static <T> void removeListener(String eventName, Object object, Consumer<T> function) {
        handler.removeListener(eventName, object);
    }

    public static void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
