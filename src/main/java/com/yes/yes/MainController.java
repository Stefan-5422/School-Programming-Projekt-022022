package com.yes.yes;

import com.yes.yes.managers.GameManager;
import com.yes.yes.managers.PlayerManager;
import com.yes.yes.managers.UiManager;
import com.yes.yes.utils.GlobalEventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    private VBox root;

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