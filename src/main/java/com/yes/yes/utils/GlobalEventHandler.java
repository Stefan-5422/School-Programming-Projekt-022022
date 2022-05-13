package com.yes.yes.utils;

import java.util.function.Consumer;

public final class GlobalEventHandler {
    private static EventHandler handler = new EventHandler();

    private GlobalEventHandler() {
    }

    public static <T> void addListener(String eventName, Object object, Consumer<T> function) {
        handler.addListener(eventName, object, function);
    }

    public static void removeListener(String eventName, Object object) {
        handler.removeListener(eventName, object);
    }

    public static void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
