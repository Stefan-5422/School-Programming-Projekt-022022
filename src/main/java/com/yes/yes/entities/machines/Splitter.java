package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.behaviours.SplitBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Splitter extends Entity {
    public Splitter() {
        super();
        Polygon p1 = new Polygon();
        p1.getPoints().addAll(
                0.0, Chunk.ENTITY_SIZE / 2d,
                Chunk.ENTITY_SIZE / 2d, 0.0,
                (double) Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE / 2d,
                Chunk.ENTITY_SIZE / 4d * 3, (double) Chunk.ENTITY_SIZE,
                Chunk.ENTITY_SIZE / 2d, Chunk.ENTITY_SIZE / 2d,
                Chunk.ENTITY_SIZE / 4d, (double) Chunk.ENTITY_SIZE
        );
        p1.setFill(new Color(0, 0, 0, 1));

        this.getChildren().addAll(p1);
    }

    public Splitter(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new SplitBehaviour(this, blockContainer, 1, "ReceivedItem", "OfferItemLeft", "OfferItemRight", "OfferItemUp"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "ReceivedItem"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.LEFT, "OfferItemLeft"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.RIGHT, "OfferItemRight"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "OfferItemUp"));
    }
}
