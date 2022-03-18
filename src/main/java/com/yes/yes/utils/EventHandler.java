package com.yes.yes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class EventHandler {
    private final HashMap<String, ArrayList<Function<Object, Void>>> events = new HashMap<>();



    public void addListener(String eventName, Function<Object, Void> function) {
        if (!events.containsKey(eventName)) {
            events.put(eventName, new ArrayList<>());
        }
        events.get(eventName).add(function);
    }

    public void removeListener(String eventName, Function<Object, Void> function) throws IllegalArgumentException {
        //NOTE: This needs to be called otherwise memory leak
        if (!events.containsKey(eventName)) {
            throw new IllegalArgumentException("Event " + eventName + " does not exist!");
        }
        events.get(eventName).remove(function);
    }

    public void trigger(String eventName, Object parameter) {
        //TODO: Check if this needs null checking
        if (events.containsKey(eventName)) {
            events.get(eventName).forEach(e -> e.apply(parameter));
        }
    }
}
