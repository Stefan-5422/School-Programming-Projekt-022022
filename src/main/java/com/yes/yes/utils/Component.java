package com.yes.yes.utils;

public abstract class Component {
    protected Entity parent;


    public Component(Entity entity) {
        this.parent = entity;
    }

    public abstract void initialize();
    public abstract void update();
}
