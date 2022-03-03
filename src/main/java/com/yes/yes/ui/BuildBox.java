package com.yes.yes.ui;

import com.yes.yes.utils.EntityRegistry;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.function.Function;

public class BuildBox extends javafx.scene.Group {
    String selectedEntity;


    Function<String, Void> changeItem = i -> {
        System.out.println(i);
        this.selectedEntity = i;
        return null;
    };

    public BuildBox() {
        ScrollPane pane = new ScrollPane();
        HBox hbox = new HBox();
        hbox.prefHeight(100);
        hbox.maxHeight(100);
        hbox.prefWidth(Double.POSITIVE_INFINITY);

        pane.setContent(hbox);

        this.getChildren().add(pane);

        this.setViewOrder(-1);

        Platform.runLater(() -> pane.prefWidthProperty().bind(this.getScene().getWindow().widthProperty()));

        EntityRegistry.getKeys().forEach(e -> hbox.getChildren().add(new BuildItem(e, changeItem)));
    }

    public String getSelectedEntity() {
        return selectedEntity;
    }
}
