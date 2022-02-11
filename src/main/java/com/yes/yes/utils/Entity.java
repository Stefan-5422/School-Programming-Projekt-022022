package com.yes.yes.utils;

import java.util.ArrayList;

public abstract class Entity extends javafx.scene.Group {
    private ArrayList<Component> behavours = new ArrayList<>();

    public void addBehaviour(Component behaviour) {
        behavours.add(behaviour);
    }

    public void removeBehaviour(Component behaviour) {
        ArrayList<Component> components = new ArrayList<>();
        for (Component b : behavours) {
            if (!b.getClass().equals(behaviour.getClass())) {
                components.add(b);
            }
        }
        behavours = components;
    }

    public void update() {
        for (Component behaviour : behavours) {
            System.out.println("Updating " + behaviour.getClass().getSimpleName());
        }
    }


}
