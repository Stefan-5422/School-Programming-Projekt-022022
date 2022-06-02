package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.behaviours.StackBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Stacker extends Entity {

    public Stacker() {
        super();
        Polygon p1 = new Polygon();
        p1.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                0.0, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE);
        p1.setFill(Color.BLACK);

        Polygon p2 = new Polygon();
        p2.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, Chunk.ENTITY_SIZE / 2d,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE / 4d,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE / 4d * 3);
        p2.setFill(Color.GRAY);

        this.getChildren().addAll(p1, p2);
    }

    @SuppressWarnings("unused") // Called dynamically
    public Stacker(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new StackBehaviour(this, blockContainer, "Input1", "Input2", "Output", 2));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "Output"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "Input1"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.RIGHT, "Input2"));
    }
}
