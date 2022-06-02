package com.yes.yes;

import com.yes.yes.managers.GameManager;
import com.yes.yes.managers.HubManager;
import com.yes.yes.managers.PlayerManager;
import com.yes.yes.managers.UiManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    private VBox root;

    public void initialize() {
        Platform.runLater(() -> {
            GameManager gameManager = new GameManager(root);
            gameManager.initialize();

            UiManager uiManager = new UiManager(root);
            uiManager.initialize();

            PlayerManager playerManager = new PlayerManager(gameManager.getWorld(), uiManager.getBuildBox());
            playerManager.initialize();

            HubManager hubManager = new HubManager();
            hubManager.initialize();
        });

    }
}