package com.yes.yes.world;

import com.yes.yes.utils.Component;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Chunk extends GridPane {
    private static final int CHUNK_SIZE = 16;
    private static final double COMPONENT_SIZE = 50;
    private final Component[] data = new Component[CHUNK_SIZE * CHUNK_SIZE];

    public Chunk() {
        super();
        this.setMaxSize(CHUNK_SIZE * COMPONENT_SIZE, CHUNK_SIZE * COMPONENT_SIZE);
        this.setMinSize(CHUNK_SIZE * COMPONENT_SIZE, CHUNK_SIZE * COMPONENT_SIZE);

        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                this.getColumnConstraints().add(new ColumnConstraints(COMPONENT_SIZE));
                this.getRowConstraints().add(new RowConstraints(COMPONENT_SIZE));


                Rectangle rect = new Rectangle(COMPONENT_SIZE, COMPONENT_SIZE);
                rect.setFill(Color.color((i+j*16)/257d,(i+j*16)/257d, (i+j*16)/257d,1)); //DEBUG CODE
                rect.setStroke(Color.BLACK);
                rect.strokeWidthProperty().set(0.5);
                this.add(rect, i, j);
            }
        }
    }

    public void setComponent(Component component, int x, int y) {
        data[x + y * CHUNK_SIZE] = component;
        this.add(component, x, y);
    }

    public void removeComponent(int x, int y) {
        this.getChildren().remove(data[x + y * CHUNK_SIZE]);
        data[x + y * CHUNK_SIZE] = null;
    }

    public Component getComponent(int x, int y) {
        return data[x + y * CHUNK_SIZE];
    }


}
