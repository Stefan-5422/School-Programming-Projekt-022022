package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class TestMachine extends Entity {
    public TestMachine() {
        super();
        Circle c = new Circle(25);
        c.setCenterX(25);
        c.setCenterY(25);

        Polygon t = new Polygon();
        t.getPoints().addAll(
                25.0, 0.0,
                0.0, 50.0,
                50.0, 50.0);
        t.setFill(new Color(1, 1, 1, 0.5));

        this.getChildren().addAll(c, t);
    }

    public TestMachine(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new TestBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "Item"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "Item"));
    }
}
