package com.yes.yes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Entity extends javafx.scene.Group {
    private final HashMap<String, Object> data = new HashMap<>();
    private final EventHandler handler = new EventHandler();
    private ArrayList<Component> behaviours = new ArrayList<>();
    private int rotation;

    public final void addBehaviour(Component behaviour) {
        behaviours.add(behaviour);
        behaviour.initialize();
    }

    public final void removeBehaviour(Component behaviour) {
        ArrayList<Component> components = new ArrayList<>();
        for (Component b : behaviours) {
            if (!b.getClass().equals(behaviour.getClass())) {
                components.add(b);
            }
        }
        behaviours = components;
    }

    public final void update() {
        for (Component behaviour : behaviours) {
            System.out.println("Updating " + behaviour.getClass().getSimpleName());
            behaviour.update();
        }
    }

    public final void setData(String key, Object value) {
        data.put(key, value);
    }

    public final <T> T getData(String key) {
        return (T) data.get(key);
    }

    public final <T> void addListener(String eventName, Consumer<T> function) {
        handler.addListener(eventName, function);
    }

    public final void removeListener(String eventName, Function<Object, Void> function) {
        handler.removeListener(eventName, function);
    }

    public final void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }
}
