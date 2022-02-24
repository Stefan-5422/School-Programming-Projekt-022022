package com.yes.yes.world;

import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.exceptions.ChunkAlreadyExistsException;
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
        for (int i = 0; i < (Integer.MAX_VALUE/1000/(Chunk.CHUNK_SIZE*Chunk.ENTITY_SIZE)); i++) {
            this.getRowConstraints().add(new RowConstraints(Chunk.CHUNK_SIZE*Chunk.ENTITY_SIZE));
            this.getColumnConstraints().add(new ColumnConstraints(Chunk.CHUNK_SIZE*Chunk.ENTITY_SIZE));
        }
    }

    public void unload(Coordinate pos) {
        Chunk chunk = getChunk(pos);
        this.getChildren().remove(chunk);
        loadedChunks.remove(pos);

        System.out.println("Unloading: " + pos.x + "; " + pos.y);
    }

    public void load(Coordinate pos) {
        if(loadedChunks.containsKey(pos)) return;

        Chunk chunk = getChunk(pos);
        this.add(chunk, pos.x, pos.y);
        loadedChunks.put(pos, chunk);
        System.out.println("Loading: " + pos.x + "; " + pos.y);
    }

    public void addChunk(Coordinate pos) throws ChunkAlreadyExistsException {
        if (chunks.get(pos) == null) {
            chunks.put(pos, new Chunk());
        } else {
            throw new ChunkAlreadyExistsException();
        }
    }

    public Chunk getChunk(Coordinate pos) {
        Chunk c = chunks.get(pos);
        if (c == null) {
            c = new Chunk();
            chunks.put(pos, c);
        }
        return c;
    }
}
