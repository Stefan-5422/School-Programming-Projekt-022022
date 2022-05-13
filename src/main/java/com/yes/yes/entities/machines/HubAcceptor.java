package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.ReceiveBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HubAcceptor extends Entity {
    public HubAcceptor() {
        Rectangle r = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        r.setFill(Color.PINK);

        this.getChildren().add(r);
    }

    public HubAcceptor(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.LEFT, "Input"));
    }
}
