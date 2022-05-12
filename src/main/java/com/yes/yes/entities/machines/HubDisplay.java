package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.behaviours.RecieveBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HubDisplay extends Entity {

    public HubDisplay() {
        Rectangle r = new Rectangle(50, 50);
        r.setFill(Color.PINK);

        Text text = new Text("yes");

        this.getChildren().addAll(r, text);
    }

    public HubDisplay(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "objective"));
    }
}
