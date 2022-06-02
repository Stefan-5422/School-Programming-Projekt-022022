package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.*;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.NotOverridable;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HubAcceptor extends Entity implements NotOverridable {
    public HubAcceptor() {
        Rectangle r = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        r.setFill(Color.PINK);

        this.getChildren().add(r);
    }

    @SuppressWarnings("unused") // Called dynamically
    public HubAcceptor(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.LEFT, "Input"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.RIGHT, "Input"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.UP, "Input"));
        this.addBehaviour(new ReceiveBehaviour(this, blockContainer, Direction.DOWN, "Input"));
        this.addBehaviour(new DataStoreEvent(this, blockContainer, "Input", "hub:objectiveCompletion", true));
        this.addBehaviour(new DestroyBehaviour(this, blockContainer, "Input"));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "Objective"));
        this.addBehaviour(new EventDataStore(this, blockContainer, "hub:newObjective", "Objective", true));
    }
}
