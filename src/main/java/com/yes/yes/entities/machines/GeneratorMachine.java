package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.GeneratorBehaviour;
import com.yes.yes.behaviours.ItemBehaviour;
import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class GeneratorMachine extends Entity {
    public GeneratorMachine() {
        super();
        Rectangle r = new Rectangle(50, 50);
        r.setFill(Color.RED);

        Polygon p = new Polygon();
        p.getPoints().addAll(
                25.0, 0.0,
                0.0, 50.0,
                50.0, 50.0);
        p.setFill(new Color(0, 0, 0, 0.5));

        this.getChildren().addAll(r, p);
    }

    public GeneratorMachine(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new ItemBehaviour(this, blockContainer, "Item"));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "Item"));
        this.addBehaviour(new GeneratorBehaviour(this, blockContainer, generateItem(), "Item"));
    }

    private Item generateItem() {
        Item it = new Item();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                it.setPart(i, j, new Square());
            }
        }
        return it;
    }
}
