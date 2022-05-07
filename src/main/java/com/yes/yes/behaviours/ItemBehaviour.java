package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

public class ItemBehaviour extends Component {

    private VBox itemGroup;
    private String dataKey;

    public ItemBehaviour(Entity entity, BlockContainer blockContainer, String dataKey) {
        super(entity, blockContainer);
        this.dataKey = dataKey;
    }

    private void itemChanged(Item item) {
        System.out.println("Running item update");
        Platform.runLater(() -> {
            this.itemGroup.getChildren().clear();
            if (item != null)
                this.itemGroup.getChildren().add(item);
        });
        System.out.println("Finished item update");
    }

    @Override
    public void initialize() {
        parent.addListener(dataKey + "Changed", this, this::itemChanged);

        itemGroup = new VBox();
        itemGroup.setAlignment(Pos.CENTER);

        this.parent.getChildren().add(itemGroup);

        Scale s = new Scale(0.7, 0.7);
        itemGroup.getTransforms().add(s);
    }

    @Override
    public void update() {
        System.out.println("Destroyed:" + parent.getData("destroyed"));
    }

    @Override
    public void destroy() {
        parent.removeListener(dataKey + "Changed", this, this::itemChanged);
    }
}
