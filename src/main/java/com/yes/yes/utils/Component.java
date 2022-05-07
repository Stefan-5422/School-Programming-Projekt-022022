package com.yes.yes.utils;

public abstract class Component {
    protected final Entity parent;
    protected final BlockContainer blockContainer;

    public Component(Entity entity, BlockContainer blockContainer) {
        this.parent = entity;
        this.blockContainer = blockContainer;
    }

    public abstract void initialize();

    public abstract void update();
    public abstract void destroy();
}
