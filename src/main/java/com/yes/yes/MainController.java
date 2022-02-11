package com.yes.yes;

import com.yes.yes.world.World;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    VBox root;

    public void initialize() {
        World chunk = new World();

        root.getChildren().add(chunk);
    }
}