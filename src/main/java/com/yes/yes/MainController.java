package com.yes.yes;

import com.yes.yes.managers.GameManager;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    VBox root;

    public void initialize() {
        GameManager gameManager = new GameManager(root);
        gameManager.setup();
    }
}