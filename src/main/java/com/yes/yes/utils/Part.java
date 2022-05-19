package com.yes.yes.utils;

import javafx.scene.paint.Color;

public abstract class Part extends javafx.scene.Group {
    Color color;

    public void rotate(int quarters) {

        this.getTransforms().clear();
        this.getTransforms().add(new javafx.scene.transform.Rotate(quarters % 4 * 90, 0, 0));
    }

    protected abstract void onColorChanged(Color color);

    public void setColor(Color color) {
        this.color = color;
        onColorChanged(color);
    }

    public Color getColor() {
        return color;
    }
}
