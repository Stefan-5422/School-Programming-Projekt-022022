package com.yes.yes.utils;

import java.util.ArrayList;

public abstract class Entity extends javafx.scene.Group {
    private ArrayList<Component> behaviours = new ArrayList<>();

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
}
