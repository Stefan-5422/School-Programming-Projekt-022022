package com.yes.yes.entities.machines;

import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;
import javafx.scene.shape.Circle;

public class TestMachine extends Entity {

    public TestMachine() {
        super();
        this.getChildren().add(new Circle(25));
    }
}
