package com.yes.yes.entities.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Square extends com.yes.yes.utils.Part {

    Rectangle rect = new Rectangle(23, 23);

    public Square(Color color)
    {
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
    public void onColorChanged(Color color)
    {
        rect.setFill(color);
    }
}
