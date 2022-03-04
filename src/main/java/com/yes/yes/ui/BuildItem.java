package com.yes.yes.ui;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.world.Chunk;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.function.Function;

public class BuildItem extends javafx.scene.layout.StackPane {
    String display_name;
    String name;
    Class<?> entity;

    public BuildItem(String name, Function<String, Void> onClick) {
        super();

        this.display_name = EntityRegistry.getEntity(name).getDisplayName();
        this.entity = EntityRegistry.getEntity(name).getEntity();
        this.name = name;

        this.prefWidth(Chunk.ENTITY_SIZE);
        this.prefHeight(Chunk.ENTITY_SIZE + 10);

        try {
            this.getChildren().add((Node) entity.getConstructor().newInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Button button = new Button();

        button.setStyle("-fx-background-color: null; -fx-border-color: null");
        button.setPrefSize(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        button.setMinSize(Chunk.ENTITY_SIZE, Chunk.ENTITY_SIZE);
        button.setOnAction((e) -> onClick.apply(this.name));

        this.getChildren().add(button);
    }
}
