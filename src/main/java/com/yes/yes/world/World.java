package com.yes.yes.world;

import com.yes.yes.utils.Coordinate;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class World extends GridPane {
    private final HashMap<Coordinate,Chunk> chunks = new HashMap<>();

    public World() {
        super();

        for (int x = 0; x < 4; x++) {//DEBUG CODE
            for (int y = 0; y < 4; y++) {
                chunks.put(new Coordinate(x, y), new Chunk());
                this.add(chunks.get(new Coordinate(x, y)), x, y);
            }
        }

        this.unload(new Coordinate(2, 2)); //DEBUG CODE
    }

    public void unload(Coordinate pos) {
        this.getChildren().remove(chunks.get(pos));
    }
    public void load(Coordinate pos) {
        this.add(chunks.get(pos), pos.x, pos.y);
    }
}
