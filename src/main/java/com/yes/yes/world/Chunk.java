package com.yes.yes.world;

import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.NoiseGenerator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Chunk extends GridPane {
    public static final int CHUNK_SIZE = 8;
    public static final int ENTITY_SIZE = 50;
    private final Entity[] data = new Entity[CHUNK_SIZE * CHUNK_SIZE];
    private final Color chunkColor;

    public Chunk(Coordinate pos) {
        super();
        this.setMaxSize(CHUNK_SIZE * ENTITY_SIZE, CHUNK_SIZE * ENTITY_SIZE);
        this.setMinSize(CHUNK_SIZE * ENTITY_SIZE, CHUNK_SIZE * ENTITY_SIZE);

        chunkColor = switch (((int) NoiseGenerator.calculate(pos.x * pos.y)) % 3) {
            case 0 -> Color.RED;
            case 1 -> Color.GREEN;
            case 2 -> Color.BLUE;
            default -> throw new IllegalStateException();
        };

        Color fillColor = new Color(chunkColor.getRed(),chunkColor.getGreen(), chunkColor.getBlue(), 0.1);

        this.setBackground(new Background(new BackgroundFill(fillColor, CornerRadii.EMPTY, Insets.EMPTY)));

        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                this.getColumnConstraints().add(new ColumnConstraints(ENTITY_SIZE));
                this.getRowConstraints().add(new RowConstraints(ENTITY_SIZE));


                Rectangle rect = new Rectangle(ENTITY_SIZE, ENTITY_SIZE);
                rect.setFill(null);
                rect.setStroke(Color.BLACK);
                rect.strokeWidthProperty().set(0.5);
                this.add(rect, i, j);

                this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public void setEntity(Entity component, Coordinate pos) {
        removeEntity(pos);
        data[pos.x + pos.y * CHUNK_SIZE] = component;
        this.add(component, pos.x, pos.y);
        GridPane.setValignment(component, VPos.CENTER);
        GridPane.setHalignment(component, HPos.CENTER);
    }

    public void removeEntity(Coordinate pos) {
        Entity entity = getEntity(pos);

        if (entity != null) {
            entity.destroy();
        }
        this.getChildren().remove(entity);
        data[pos.x + pos.y * CHUNK_SIZE] = null;
    }

    public Color getChunkColor() {
        return chunkColor;
    }

    public Entity getEntity(Coordinate pos) {
        return data[pos.x + pos.y * CHUNK_SIZE];
    }
}
