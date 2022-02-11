package com.yes.yes.entities.machines;

import com.yes.yes.utils.Entity;
import javafx.scene.shape.Circle;

public class TestMachine extends Entity {

    public TestMachine() {
        super();
        Circle shape = new Circle(25);
        shape.setStyle("-fx-fill: #ff0000");
        this.getChildren().add(shape);
    }
}
