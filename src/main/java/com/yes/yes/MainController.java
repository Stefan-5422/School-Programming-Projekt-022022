package com.yes.yes;

import com.yes.yes.managers.GameManager;
import com.yes.yes.managers.PlayerManager;
import com.yes.yes.utils.Coordinate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    VBox root;

    public void initialize() {
        GameManager gameManager = new GameManager(root);
        gameManager.setup();

        Platform.runLater(() -> {
            PlayerManager playerManager = new PlayerManager(gameManager.getWorld());
            playerManager.setup();
        });

    }
}