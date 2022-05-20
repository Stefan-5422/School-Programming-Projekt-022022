package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.CutItemBehaviour;
import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Orientation;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Cutter extends Entity {
    public Cutter() {
        super();

        Circle c = new Circle(Chunk.ENTITY_SIZE / 2);
        c.setFill(Color.BLACK);
        c.setCenterX(Chunk.ENTITY_SIZE / 2);
        c.setCenterY(Chunk.ENTITY_SIZE / 2);

        Polygon p = new Polygon();
        p.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                Chunk.ENTITY_SIZE / 4d, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE / 4d * 3, (double) Chunk.ENTITY_SIZE);
        p.setFill(new Color(1, 1, 1, 0.5));


        this.getChildren().addAll(c, p);
    }

    @SuppressWarnings("unused") // Called dynamically
    public Cutter(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "Item"));
        this.addBehaviour(new CutItemBehaviour(this, blockContainer, Orientation.Horizontal, "ReceivedItem", "OfferItemLeft", "OfferItemRight", 5));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "ReceivedItem"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.LEFT, "OfferItemLeft"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.RIGHT, "OfferItemRight"));
    }
}
