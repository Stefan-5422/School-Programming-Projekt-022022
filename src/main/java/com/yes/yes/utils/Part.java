package com.yes.yes.utils;

import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Part extends javafx.scene.Group {
    private Color color;

    public void rotate(int quarters) {

        this.getTransforms().clear();
        this.getTransforms().add(new javafx.scene.transform.Rotate(quarters % 4 * 90, 0, 0));
    }

    protected abstract void onColorChanged(Color color);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        onColorChanged(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part part)) return false;
        return Objects.equals(color, part.color) && o.getClass().getSimpleName().equals(this.getClass().getSimpleName());
    }
}
