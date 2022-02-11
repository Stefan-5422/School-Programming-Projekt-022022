package com.yes.yes.world;

import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Chunk extends GridPane {
    private static final int CHUNK_SIZE = 8;
    private static final double ENTITY_SIZE = 50;
    private final Entity[] data = new Entity[CHUNK_SIZE * CHUNK_SIZE];

    public Chunk() {
        super();
        this.setMaxSize(CHUNK_SIZE * ENTITY_SIZE, CHUNK_SIZE * ENTITY_SIZE);
        this.setMinSize(CHUNK_SIZE * ENTITY_SIZE, CHUNK_SIZE * ENTITY_SIZE);

        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                this.getColumnConstraints().add(new ColumnConstraints(ENTITY_SIZE));
                this.getRowConstraints().add(new RowConstraints(ENTITY_SIZE));


                Rectangle rect = new Rectangle(ENTITY_SIZE, ENTITY_SIZE);
                //rect.setFill(Color.color((i+j*16)/257d,(i+j*16)/257d, (i+j*16)/257d,1)); //DEBUG CODE
                rect.setFill(null);
                rect.setStroke(Color.BLACK);
                rect.strokeWidthProperty().set(0.5);
                this.add(rect, i, j);

                this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public void setEntity(Entity component, Coordinate pos) {
        data[pos.x + pos.y * CHUNK_SIZE] = component;
        this.add(component, pos.x, pos.y);
    }

    public void removeEntity(Coordinate pos) {
        this.getChildren().remove(data[pos.x + pos.y * CHUNK_SIZE]);
        data[pos.x + pos.y * CHUNK_SIZE] = null;
    }

    public Entity getEntity(Coordinate pos) {
        return data[pos.x + pos.y * CHUNK_SIZE];
    }
}
