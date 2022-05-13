package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.*;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class ConveyorBelt extends Entity {
    public ConveyorBelt() {
        super();
        Polygon t = new Polygon();
        t.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                0.0, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE);
        t.setFill(new Color(0, 0, 0, 1));

        this.getChildren().addAll(t);
    }

    public ConveyorBelt(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "ReceivedItem"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "ReceivedItem"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.LEFT, "ReceivedItem"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.RIGHT, "ReceivedItem"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "OfferItem"));
        this.addBehaviour(new ConveyorBehaviour(this, blockContainer, "ReceivedItem", "OfferItem", 1));
    }
}
