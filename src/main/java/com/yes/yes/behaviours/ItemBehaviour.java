package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;
import com.yes.yes.world.Chunk;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class ItemBehaviour extends Component {

    private StackPane itemGroup;
    private final String dataKey;

    public ItemBehaviour(Entity entity, BlockContainer blockContainer, String dataKey) {
        super(entity, blockContainer);
        this.dataKey = dataKey;
    }

    private void itemChanged(Item item) {
        //System.out.println("Running item update");
        Platform.runLater(() -> {
            this.itemGroup.getChildren().clear();
            if (item != null)
                this.itemGroup.getChildren().add(item);
            else {
                Rectangle alignmentRectangle = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
                alignmentRectangle.setFill(Color.rgb(0,0,0,0));
                this.itemGroup.getChildren().add(alignmentRectangle);
            }
        });
        //System.out.println("Finished item update");
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

        Translate translate = new Translate(Chunk.ENTITY_SIZE/4,Chunk.ENTITY_SIZE/4);
        itemGroup.getTransforms().add(translate);

        Rectangle alignmentRectangle = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        alignmentRectangle.setFill(Color.rgb(0,0,0,0));
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
