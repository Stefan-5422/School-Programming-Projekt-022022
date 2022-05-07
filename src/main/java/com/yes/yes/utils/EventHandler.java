package com.yes.yes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class EventHandler {
    private final HashMap<String, ArrayList<Consumer<Object>>> events = new HashMap<>();


    public <T> void addListener(String eventName, Consumer<T> function) {
        if (!events.containsKey(eventName)) {
            events.put(eventName, new ArrayList<>());
        }

        events.get(eventName).add((e) -> function.accept((T) e));
    }

    public <T> void removeListener(String eventName, Consumer<T> function) throws IllegalArgumentException {
        //NOTE: This needs to be called otherwise memory leak
        if (!events.containsKey(eventName)) {
            throw new IllegalArgumentException("Event " + eventName + " does not exist!");
        }
        //noinspection SuspiciousMethodCalls
        events.get(eventName).remove(function);
    }

    public <T> void trigger(String eventName, T parameter) {
        if (events.containsKey(eventName)) {
            events.get(eventName).forEach(e -> e.accept(parameter));
        }
    }
}
