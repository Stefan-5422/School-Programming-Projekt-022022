package com.yes.yes.entities.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EmptyPart extends com.yes.yes.utils.Part {

    public EmptyPart() {
        Rectangle rect = new Rectangle(23, 23);
        rect.setFill(new Color(0,0,0,0));
        this.getChildren().add(rect);
    }

    @Override
    protected void onColorChanged(Color color) {
    }
}