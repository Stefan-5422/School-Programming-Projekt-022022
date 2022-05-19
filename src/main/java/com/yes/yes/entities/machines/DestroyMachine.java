package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.DestroyBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class DestroyMachine extends Entity {
    public DestroyMachine() {
        super();
        Circle c = new Circle(Chunk.ENTITY_SIZE / 2);
        c.setCenterX(Chunk.ENTITY_SIZE / 2);
        c.setCenterY(Chunk.ENTITY_SIZE / 2);

        Polygon t = new Polygon();
        t.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                0.0, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE);
        t.setFill(new Color(1, 1, 1, 0.5));

        this.getChildren().addAll(c, t);
    }

    @SuppressWarnings("unused") // Called dynamically
    public DestroyMachine(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "Item"));
        this.addBehaviour(new DestroyBehaviour(this, blockContainer, "Item"));
    }
}
