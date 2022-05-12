package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.RecieveBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HubAcceptor extends Entity {
    public HubAcceptor() {
        Rectangle r = new Rectangle(50, 50);
        r.setFill(Color.PINK);

        this.getChildren().add(r);
    }

    public HubAcceptor(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new RecieveBehaviour(this, blockContainer, Direction.LEFT, "Input"));
    }
}