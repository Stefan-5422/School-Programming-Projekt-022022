package com.yes.yes.entities.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends com.yes.yes.utils.Part {
    public Square() {
        var rect = new Rectangle(23, 23);
        rect.setFill(Color.RED);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        this.getChildren().add(rect);
    }
}
