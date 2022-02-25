package com.yes.yes.ui;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class BuildBox
        extends javafx.scene.Group {
    public BuildBox() {
        ScrollPane pane = new ScrollPane();
        HBox hbox = new HBox();
        hbox.prefHeight(100);
        hbox.maxHeight(100);
        hbox.prefWidth(Double.POSITIVE_INFINITY);

        pane.setContent(hbox);

        this.getChildren().add(pane);

        Platform.runLater(() ->
                {
                    pane.prefWidthProperty()
                            .bind(this.getScene()
                                    .getWindow()
                                    .widthProperty());
                }
        );
    }

}
