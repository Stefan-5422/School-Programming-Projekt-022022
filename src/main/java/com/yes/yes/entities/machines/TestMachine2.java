package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.world.Chunk;
import javafx.scene.shape.Rectangle;

public class TestMachine2 extends Entity {
    public TestMachine2() {
        super();
        this.getChildren().add(new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE));
    }

    public TestMachine2(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new TestBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "Item"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.RIGHT, "Item"));
    }
}