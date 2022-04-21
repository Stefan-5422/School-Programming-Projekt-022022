package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.behaviours.TestRecieveBeahaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Entity;
import javafx.scene.shape.Circle;

public class TestMachine extends Entity {
    public TestMachine() {
        super();
        Circle c = new Circle(25);
        c.setCenterX(25);
        c.setCenterY(25);
        this.getChildren().add(c);
    }

    public TestMachine(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new TestBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer));
        this.addBehaviour(new TestRecieveBeahaviour(this, blockContainer));
    }
}
