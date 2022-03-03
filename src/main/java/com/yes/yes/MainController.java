package com.yes.yes;

import com.yes.yes.managers.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    VBox root;

    public void initialize() {
        GameManager gameManager = new GameManager(root);
        gameManager.initialize();

        Platform.runLater(() -> {
            UiManager uiManager = new UiManager(root);
            uiManager.initialize();

            PlayerManager playerManager = new PlayerManager(gameManager.getWorld(), uiManager.getBuildBox());
            playerManager.initialize();
        });

    }
}