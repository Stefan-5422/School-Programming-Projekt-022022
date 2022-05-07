package com.yes.yes.managers;

import com.yes.yes.ui.BuildBox;
import com.yes.yes.utils.*;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.Scene;
import javafx.scene.input.*;

import java.util.Random;

public class PlayerManager {

    private static final KeyCombination LEFT_KEY = new KeyCodeCombination(KeyCode.A);
    private static final KeyCombination RIGHT_KEY = new KeyCodeCombination(KeyCode.D);
    private static final KeyCombination UP_KEY = new KeyCodeCombination(KeyCode.W);
    private static final KeyCombination DOWN_KEY = new KeyCodeCombination(KeyCode.S);

    private final World world;
    private final BuildBox buildBox;
    private Coordinate chunkPos = new Coordinate(0, 0);

    public PlayerManager(World world, BuildBox buildBox) {
        this.world = world;
        this.buildBox = buildBox;
    }

    public void initialize() {
        Scene scene = world.getScene();
        scene.setOnKeyPressed(this::processKeyPress);

        scene.widthProperty().addListener((e) -> redrawChunks());
        scene.heightProperty().addListener((e) -> redrawChunks());

        world.setOnMouseClicked(this::processClick);
        world.setTranslateX(Integer.MAX_VALUE / -5000d);
        world.setTranslateY(Integer.MAX_VALUE / -5000d);

        redrawChunks();
    }

    private void processClick(MouseEvent mouse) {
        Coordinate mouseCoordinate = new Coordinate((int) mouse.getX(), (int) mouse.getY());
        Coordinate chunkCoordinate = Coordinate.WorldToChunkCoordinate(mouseCoordinate);
        Coordinate blockCoordinate = Coordinate.WorldToChunkBlock(mouseCoordinate);

        try {
            Class<?>[] types = new Class<?>[1];
            types[0] = BlockContainer.class;

            BlockContainer blockContainer = new BlockContainer(world, blockCoordinate, chunkCoordinate);
            RegistryEntry registryEntry = EntityRegistry.getEntry(buildBox.getSelectedEntity());

            if (registryEntry == null)
                return;

            Entity entity = (Entity) registryEntry.getEntity().getConstructor(types).newInstance(blockContainer);

            Chunk chunk = world.getChunk(chunkCoordinate);
            Entity oldEntity = chunk.getEntity(blockCoordinate);

            if(oldEntity != null && oldEntity.getClass().getSimpleName() == entity.getClass().getSimpleName()) {
                entity.setRotation(oldEntity.getRotation() + 1);
            }

            chunk.setEntity(entity, blockCoordinate);

            entity.initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processKeyPress(KeyEvent key) {
        if (LEFT_KEY.match(key)) {
            world.setTranslateX(world.getTranslateX() + 45);
        }
        if (RIGHT_KEY.match(key)) {
            world.setTranslateX(world.getTranslateX() - 45);
        }
        if (UP_KEY.match(key)) {
            world.setTranslateY(world.getTranslateY() + 45);
        }
        if (DOWN_KEY.match(key)) {
            world.setTranslateY(world.getTranslateY() - 45);
        }


        Size preferredAmountOfChunks = getPreferredAmountOfChunks();
        Coordinate chunkPosition = getCurrentChunkPosition();

        if (chunkPosition.x < chunkPos.x) {
            for (int i = 0; i < preferredAmountOfChunks.y; i++) {
                world.loadChunk(new Coordinate(chunkPosition.x, chunkPosition.y + i));
                world.unloadChunk(new Coordinate(chunkPos.x + preferredAmountOfChunks.x - 1, chunkPosition.y + i));
            }
        }

        if (chunkPosition.x > chunkPos.x) {
            for (int i = 0; i < preferredAmountOfChunks.y; i++) {
                world.loadChunk(new Coordinate(chunkPosition.x + preferredAmountOfChunks.x - 1, chunkPosition.y + i));
                world.unloadChunk(new Coordinate(chunkPos.x, chunkPosition.y + i));
            }
        }

        if (chunkPosition.y > chunkPos.y) {
            for (int i = 0; i < preferredAmountOfChunks.x; i++) {
                world.loadChunk(new Coordinate(chunkPosition.x + i, chunkPosition.y + preferredAmountOfChunks.y - 1));
                world.unloadChunk(new Coordinate(chunkPosition.x + i, chunkPos.y));
            }
        }

        if (chunkPosition.y < chunkPos.y) {
            for (int i = 0; i < preferredAmountOfChunks.x; i++) {
                world.loadChunk(new Coordinate(chunkPosition.x + i, chunkPosition.y));
                world.unloadChunk(new Coordinate(chunkPosition.x + i, chunkPos.y + preferredAmountOfChunks.y - 1));
            }
        }

        chunkPos = new Coordinate(chunkPosition.x, chunkPosition.y);
    }

    private void redrawChunks() {
        Size preferredAmountOfChunks = getPreferredAmountOfChunks();
        Coordinate chunkPosition = getCurrentChunkPosition();

        world.unloadAllChunks();

        for (int y = 0; y < preferredAmountOfChunks.y; y++) {
            for (int x = 0; x < preferredAmountOfChunks.x; x++) {
                world.loadChunk(new Coordinate(chunkPosition.x + x, chunkPosition.y + y));
            }
        }
    }

    private Coordinate getCurrentChunkPosition() {
        double offset = Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;

        int chunkX = -(int) Math.floor((world.getTranslateX() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);
        int chunkY = -(int) Math.floor((world.getTranslateY() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);

        return new Coordinate(chunkX, chunkY);
    }

    private Size getPreferredAmountOfChunks() {
        int loadedChunksX = (int) Math.ceil(world.getScene().getWidth() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;
        int loadedChunksY = (int) Math.ceil(world.getScene().getHeight() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;

        return new Size(loadedChunksX, loadedChunksY);
    }
}
