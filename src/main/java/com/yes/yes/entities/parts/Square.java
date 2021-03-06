package com.yes.yes.entities.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends com.yes.yes.utils.Part {

    Rectangle rect;

    public Square(Color color) {
        this();
        setColor(color);
    }

    public Square() {
        rect = new Rectangle(23, 23);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        this.getChildren().add(rect);
    }

    @Override
    protected void onColorChanged(Color color) {
        rect.setFill(color);
    }
}
