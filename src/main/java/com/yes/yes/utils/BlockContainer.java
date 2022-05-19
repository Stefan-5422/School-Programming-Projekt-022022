package com.yes.yes.utils;

import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;

public record BlockContainer(World world, Coordinate blockCoordinate, Coordinate chunkCoordinate) {
    final static int MAX_RADIUS = 2;

    public Entity getBlockAbsolute(Coordinate offset) throws IllegalArgumentException {
        if (Math.abs(offset.x) > MAX_RADIUS || Math.abs(offset.y) > MAX_RADIUS)
            throw new IllegalArgumentException();

        Coordinate localChunkCoordinate = new Coordinate(chunkCoordinate.x, chunkCoordinate.y);
        Coordinate localBlockCoordinate = new Coordinate(blockCoordinate.x + offset.x, blockCoordinate.y + offset.y);

        while (localBlockCoordinate.x < 0) {
            localBlockCoordinate.x += Chunk.CHUNK_SIZE;
            localChunkCoordinate.x--;
        }

        while (localBlockCoordinate.y < 0) {
            localBlockCoordinate.y += Chunk.CHUNK_SIZE;
            localChunkCoordinate.y--;
        }

        while (localBlockCoordinate.x >= Chunk.CHUNK_SIZE) {
            localBlockCoordinate.x -= Chunk.CHUNK_SIZE;
            localChunkCoordinate.x++;
        }

        while (localBlockCoordinate.y >= Chunk.CHUNK_SIZE) {
            localBlockCoordinate.y -= Chunk.CHUNK_SIZE;
            localChunkCoordinate.y++;
        }

        Chunk chunk = world.getChunk(localChunkCoordinate);
        return chunk.getEntity(localBlockCoordinate);
    }

    // Only works with a valid direction
    public Entity getBlockRelative(Coordinate direction, int rotation) throws IllegalArgumentException {
        for (int i = 0; i < rotation % 4; i++) {
            direction = Direction.rotateRight(direction);
        }
        return getBlockAbsolute(direction);
    }
}
