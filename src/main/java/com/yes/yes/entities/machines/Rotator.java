package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.*;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Rotator extends Entity {
    public Rotator() {
        super();
        Circle c = new Circle(Chunk.ENTITY_SIZE / 2d);
        c.setFill(Color.BLACK);
        c.setCenterX(Chunk.ENTITY_SIZE / 2d);
        c.setCenterY(Chunk.ENTITY_SIZE / 2d);

        Polygon p = new Polygon();
        p.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                Chunk.ENTITY_SIZE / 4d, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE / 4d * 3, (double) Chunk.ENTITY_SIZE);
        p.setFill(new Color(1, 1, 0, 0.5));

        this.getChildren().addAll(p);
    }

    @SuppressWarnings("unused") // Called dynamically
    public Rotator(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new RotateBehaviour(this, blockContainer, Direction.RIGHT, "ReceivedItem", "OfferItem", 1));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "ReceivedItem"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "OfferItem"));
    }
}
