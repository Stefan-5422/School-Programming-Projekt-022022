package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.utils.*;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import javafx.scene.shape.Circle;

public class TestMachine extends Entity {
    public TestMachine() {
        super();
        this.getChildren().add(new Circle(25));
    }

    public TestMachine(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new TestBehaviour(this, blockContainer));
        update();
    }
}
