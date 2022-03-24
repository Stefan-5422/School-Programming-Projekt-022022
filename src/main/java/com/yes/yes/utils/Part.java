package com.yes.yes.utils;

import javafx.scene.Node;

public abstract class Part extends javafx.scene.Group {
    public void addChild(Node element) {
        this.getChildren().add(element);
    }

    public void removeChild(Node element) {
        this.getChildren().remove(element);
    }

    public void rotate(int quarters) {

        this.getTransforms().clear();
        this.getTransforms().add(new javafx.scene.transform.Rotate(quarters % 4 * 90, 1, 1));
    }
}
