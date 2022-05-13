package com.yes.yes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public abstract class Entity extends javafx.scene.Group {
    private final HashMap<String, Object> data = new HashMap<>();
    private final EventHandler handler = new EventHandler();
    private ArrayList<Component> behaviours = new ArrayList<>();
    private int rotation;

    public Entity() {
        GlobalEventHandler.addListener("global:timerTick", this, (__) -> update());
    }

    public final void addBehaviour(Component behaviour) {
        behaviours.add(behaviour);
    }

    public final void removeBehaviour(Component behaviour) {
        ArrayList<Component> components = new ArrayList<>();
        for (Component component : behaviours) {
            if (!component.getClass().equals(behaviour.getClass())) {
                components.add(component);
            } else {
                component.destroy();
            }
        }
        behaviours = components;
    }

    public final void update() {
        //System.out.println("Updating: " + getClass().getSimpleName());
        for (Component behaviour : behaviours) {
            //System.out.println("Updating " + behaviour.getClass().getSimpleName());
            behaviour.update();
        }
    }

    public final void initialize() {
        behaviours.forEach(Component::initialize);
    }

    public final void destroy() {
        GlobalEventHandler.removeListener("global:timerTick", this);
        for (Component component : behaviours) {
            component.destroy();
        }
    }

    public final void setData(String key, Object value) {
        data.put(key, value);
    }

    public final <T> T getData(String key) {
        return (T) data.get(key);
    }

    public final <T> void addListener(String eventName, Object object, Consumer<T> function) {
        handler.addListener(eventName, object, function);
    }

    public final <T> void removeListener(String eventName, Object object) {
        handler.removeListener(eventName, object);
    }

    public final void trigger(String eventName, Object parameter) {
        handler.trigger(eventName, parameter);
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        rotation %= 4;

        if (rotation != this.rotation) this.trigger("rotate", rotation);
        this.rotation = rotation;
        setRotate(rotation * 90);
    }
}
