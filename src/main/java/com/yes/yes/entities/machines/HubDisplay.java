package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.HubDisplayBehaviour;
import com.yes.yes.behaviours.PlaceBehaviour;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.NotOverridable;
import com.yes.yes.world.Chunk;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HubDisplay extends Entity implements NotOverridable {

    private final Text text;

    public HubDisplay() {
        Rectangle r = new Rectangle(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        r.setFill(Color.PINK);

        text = new Text("yes\nthis is a test");
        text.setWrappingWidth(Chunk.ENTITY_SIZE);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(r, text);
        stackPane.setAlignment(Pos.CENTER);

        this.getChildren().addAll(stackPane);
    }

    @SuppressWarnings("unused") // Called dynamically
    public HubDisplay(BlockContainer blockContainer) {
        this();
        this.addBehaviour(new PlaceBehaviour(this, blockContainer));
        this.addBehaviour(new HubDisplayBehaviour(this, blockContainer, "hub:statusText", text));
    }
}
