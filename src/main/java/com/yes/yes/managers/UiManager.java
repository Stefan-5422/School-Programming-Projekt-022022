package com.yes.yes.managers;

import com.yes.yes.ui.BuildBox;
import javafx.scene.layout.VBox;

public class UiManager {
    VBox root;
    BuildBox buildBox;

    public UiManager(VBox root) {
        this.root = root;
    }

    public void initialize() {
        buildBox = new BuildBox();
        root.getChildren().add(0, buildBox);
    }

    public BuildBox getBuildBox() {
        return buildBox;
    }
}
