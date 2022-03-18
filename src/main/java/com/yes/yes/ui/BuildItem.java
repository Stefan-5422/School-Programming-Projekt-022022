package com.yes.yes.ui;

import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.world.Chunk;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.function.Consumer;

public class BuildItem extends javafx.scene.layout.StackPane {
    private final String display_name;
    private final String name;

    public BuildItem(String name, Consumer<String> onClick) {
        super();

        this.display_name = EntityRegistry.getEntry(name).getDisplayName();
        Class<?> entity = EntityRegistry.getEntry(name).getEntity();
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
        button.setOnAction((e) -> onClick.accept(this.name));

        this.getChildren().add(button);
    }
}
