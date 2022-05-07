package com.yes.yes.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class GlobalEventHandler {
    private static final EventHandler handler = new EventHandler();

    private GlobalEventHandler() {
    }

    public static <T> void addListener(String eventName, Object object, Consumer<T> function) {
        handler.addListener(eventName,object, function);
    }

    public static <T> void removeListener(String eventName, Object object, Consumer<T> function) {
        handler.removeListener(eventName, object, function);
    }

    public static void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
