package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

public class ItemBehaviour extends Component {

    private VBox itemGroup;
    private Item item;

    public ItemBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    private void itemChanged(Item item) {
        if(this.item != null) {
            this.itemGroup.getChildren().clear();
        }
        this.item = item;

        this.itemGroup.getChildren().add(item);
    }

    @Override
    public void initialize() {
        parent.addListener("itemChanged", this::itemChanged);

        itemGroup = new VBox();
        itemGroup.setAlignment(Pos.CENTER);

        this.parent.getChildren().add(itemGroup);

        Scale s = new Scale(0.7, 0.7);
        itemGroup.getTransforms().add(s);
    }

    @Override
    public void update() {
    }
}
