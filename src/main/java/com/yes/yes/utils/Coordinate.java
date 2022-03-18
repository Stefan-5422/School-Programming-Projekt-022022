package com.yes.yes.utils;

import com.yes.yes.world.Chunk;

import java.util.Objects;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate WorldToChunkCoordinate(Coordinate worldPos) {
        int chunkSize = Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;

        return new Coordinate(worldPos.x / chunkSize, worldPos.y / chunkSize);
    }

    public static Coordinate WorldToChunkBlock(Coordinate worldPos) {
        int chunkSize = Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;
        worldPos.x = worldPos.x % chunkSize;
        worldPos.y = worldPos.y % chunkSize;

        return new Coordinate(worldPos.x / Chunk.ENTITY_SIZE, worldPos.y / Chunk.ENTITY_SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}