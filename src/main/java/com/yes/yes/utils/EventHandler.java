package com.yes.yes.utils;

import java.util.HashMap;
import java.util.function.Consumer;

public class EventHandler {
    private final HashMap<String, HashMap<Object, Consumer<Object>>> events = new HashMap<>();


    public <T> void addListener(String eventName, Object object, Consumer<T> function) {
        if (!events.containsKey(eventName)) {
            events.put(eventName, new HashMap<>());
        }

        events.get(eventName).put(object, (e) -> function.accept((T) e));
    }

    public <T> void removeListener(String eventName, Object object, Consumer<T> function) throws IllegalArgumentException {
        //NOTE: This needs to be called otherwise memory leak
        if (!events.containsKey(eventName)) {
            throw new IllegalArgumentException("Event " + eventName + " does not exist!");
        }
        //noinspection SuspiciousMethodCalls
        events.get(eventName).remove(object);
    }

    public <T> void trigger(String eventName, T parameter) {
        if (events.containsKey(eventName)) {
            events.get(eventName).values().forEach(e -> e.accept(parameter));
        }
    }
}
