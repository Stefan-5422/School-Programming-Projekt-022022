package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.GeneratorBehaviour;
import com.yes.yes.behaviours.OfferBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Direction;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;
import com.yes.yes.world.Chunk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class GeneratorMachine extends Entity {
    private final Rectangle rectangle;

    public GeneratorMachine() {
        super();
        rectangle = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        rectangle.setFill(Color.RED);

        Polygon p = new Polygon();
        p.getPoints().addAll(
                Chunk.ENTITY_SIZE / 2d, 0.0,
                0.0, (double) Chunk.ENTITY_SIZE,
                (double) Chunk.ENTITY_SIZE, (double) Chunk.ENTITY_SIZE);
        p.setFill(new Color(0, 0, 0, 0.5));

        this.getChildren().addAll(rectangle, p);
    }

    @SuppressWarnings("unused") // Called dynamically
    public GeneratorMachine(BlockContainer blockContainer) {
        this();

        rectangle.setFill(blockContainer.chunkColor());

        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new OfferBehaviour(this, blockContainer, Direction.UP, "Item"));
        this.addBehaviour(new GeneratorBehaviour(this, blockContainer, generateItem(blockContainer.chunkColor()), "Item"));
    }

    private Item generateItem(Color color) {
        Item item = new Item();
        for (int i = 0; i < 4; i++) {
            Square square = new Square(color);
            item.setPart(0, i, square);
        }
        return item;
    }
}
