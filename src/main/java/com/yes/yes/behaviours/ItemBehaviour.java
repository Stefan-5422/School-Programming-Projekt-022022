package com.yes.yes.behaviours;

import com.yes.yes.entities.parts.EmptyPart;
import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.*;
import com.yes.yes.world.Chunk;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class ItemBehaviour extends Component {

    private final String dataKey;
    private StackPane itemGroup;

    public ItemBehaviour(Entity entity, BlockContainer blockContainer, String dataKey) {
        super(entity, blockContainer);
        this.dataKey = dataKey;
    }

    private void itemChanged(Item item) {
        GlobalExecQueue.schedule(() -> {
            try {

                this.itemGroup.getChildren().clear();

                if (item != null) {
                    Item displayItem = item.clone();

                    for (int layer = 0; layer < 4; layer++) {
                        for (int quarter = 0; quarter < 4; quarter++) {
                            if(displayItem.getPart(layer,quarter) == null)
                            {
                                displayItem.setPart(layer, quarter, new EmptyPart());
                            }
                        }
                    }

                    this.itemGroup.getChildren().add(displayItem);
                }
                else {
                    Rectangle alignmentRectangle = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
                    alignmentRectangle.setFill(Color.rgb(0, 0, 0, 0));
                    this.itemGroup.getChildren().add(alignmentRectangle);
                }
            } catch (IndexOutOfBoundsException ignore) {
            }
        });
    }

    @Override
    public void initialize() {
        parent.addListener(dataKey + "Changed", this, this::itemChanged);

        itemGroup = new StackPane();
        itemGroup.setAlignment(Pos.CENTER);
        itemGroup.setRotate(-this.parent.getRotate());

        this.parent.getChildren().add(itemGroup);

        Scale s = new Scale(0.7, 0.7);
        itemGroup.getTransforms().add(s);

        Translate translate = new Translate(Chunk.ENTITY_SIZE / 4d, Chunk.ENTITY_SIZE / 4d);
        itemGroup.getTransforms().add(translate);

        Rectangle alignmentRectangle = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        alignmentRectangle.setFill(Color.rgb(0, 0, 0, 0));
        this.itemGroup.getChildren().add(alignmentRectangle);
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        parent.removeListener(dataKey + "Changed", this);
    }
}
