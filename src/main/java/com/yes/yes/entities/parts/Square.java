package com.yes.yes.entities.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Square extends com.yes.yes.utils.Part {
    final Random rand = new Random();

    public Square() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        var rect = new Rectangle(23, 23);
        rect.setFill(Color.rgb(r, g, b));
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        this.getChildren().add(rect);
    }
}
