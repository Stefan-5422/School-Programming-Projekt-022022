package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Entity;
import javafx.scene.shape.Rectangle;

public class TestMachine2 extends Entity {
    public TestMachine2() {
        super();
        this.getChildren().add(new Rectangle(25, 25));
    }

    public TestMachine2(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new TestBehaviour(this, blockContainer));
    }
}