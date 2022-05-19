package com.yes.yes.world;

import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.HashMap;

public class World extends GridPane {
    private final HashMap<Coordinate, Chunk> chunks = new HashMap<>();
    private final HashMap<Coordinate, Chunk> loadedChunks = new HashMap<>();

    public World() {
        super();
        //HACK: Move the world by Integer.MAX_VALUE/-1000 in order to avoid -indexes
        //      So we need to generate all the column indexes to make alignment correct
        for (int i = 0; i < (Integer.MAX_VALUE / 1000 / (Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE)); i++) {
            this.getRowConstraints().add(new RowConstraints(Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE));
            this.getColumnConstraints().add(new ColumnConstraints(Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE));
        }
    }

    public void unloadChunk(Coordinate pos) {
        Chunk chunk = getChunk(pos);
        this.getChildren().remove(chunk);
        loadedChunks.remove(pos);
    }

    public void loadChunk(Coordinate pos) {
        if (loadedChunks.containsKey(pos)) return;

        Chunk chunk = getChunk(pos);
        this.add(chunk, pos.x, pos.y);
        loadedChunks.put(pos, chunk);
    }

    public void addChunk(Coordinate pos) throws AlreadyExistsException {
        if (chunks.get(pos) == null) {
            chunks.put(pos, new Chunk(pos));
        } else {
            throw new AlreadyExistsException("Chunk already exists");
        }
    }

    public Chunk getChunk(Coordinate pos) {
        Chunk c = chunks.get(pos);
        if (c == null) {
            c = new Chunk(pos);
            chunks.put(pos, c);
        }
        return c;
    }

    public void unloadAllChunks() {
        for (Coordinate coordinate : chunks.keySet()) {
            unloadChunk(coordinate);
        }
    }
}
