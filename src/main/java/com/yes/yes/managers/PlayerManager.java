package com.yes.yes.managers;

import com.yes.yes.ui.BuildBox;
import com.yes.yes.utils.*;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

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
        scene.setOnKeyPressed(this::ProcessKeyPress);

       scene.widthProperty().addListener((e)-> loadAllOnScreen());
       scene.heightProperty().addListener((e)-> loadAllOnScreen());

        world.setOnMouseClicked((e) ->
                {
                    Coordinate mouseCoordinate = new Coordinate((int) e.getX(), (int) e.getY());
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

                        world.getChunk(chunkCoordinate).setEntity(entity, blockCoordinate);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        world.setTranslateX(Integer.MAX_VALUE / -5000d);
        world.setTranslateY(Integer.MAX_VALUE / -5000d);

        loadAllOnScreen();
    }

    private void ProcessKeyPress(KeyEvent key) {
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


        Size loadedChunks = getAmountOfLoadedChunks();
        Coordinate chunkPosition = getCurrentChunkPosition();

        if (chunkPosition.x < chunkPos.x) {
            for (int i = 0; i < loadedChunks.y; i++) {
                world.load(new Coordinate(chunkPosition.x, chunkPosition.y + i));
                world.unload(new Coordinate(chunkPos.x + loadedChunks.x - 1, chunkPosition.y + i));
            }
        }

        if (chunkPosition.x > chunkPos.x) {
            for (int i = 0; i < loadedChunks.y; i++) {
                world.load(new Coordinate(chunkPosition.x + loadedChunks.x - 1, chunkPosition.y + i));
                world.unload(new Coordinate(chunkPos.x, chunkPosition.y + i));
            }
        }

        if (chunkPosition.y > chunkPos.y) {
            for (int i = 0; i < loadedChunks.x; i++) {
                world.load(new Coordinate(chunkPosition.x + i, chunkPosition.y + loadedChunks.y - 1));
                world.unload(new Coordinate(chunkPosition.x + i, chunkPos.y));
            }
        }

        if (chunkPosition.y < chunkPos.y) {
            for (int i = 0; i < loadedChunks.x; i++) {
                world.load(new Coordinate(chunkPosition.x + i, chunkPosition.y));
                world.unload(new Coordinate(chunkPosition.x + i, chunkPos.y + loadedChunks.y - 1));
            }
        }

        chunkPos = new Coordinate(chunkPosition.x, chunkPosition.y);
    }

    private void loadAllOnScreen()
    {
        Size loadedChunks = getAmountOfLoadedChunks();
        Coordinate chunkPosition = getCurrentChunkPosition();

        for (int y = 0; y < loadedChunks.y; y++) {
            for (int x = 0; x < loadedChunks.x; x++) {
                world.load(new Coordinate(chunkPosition.x + x, chunkPosition.y + y));
            }
        }
    }

    private Coordinate getCurrentChunkPosition()
    {
        double offset = Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;

        int chunkX = -(int) Math.floor((world.getTranslateX() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);
        int chunkY = -(int) Math.floor((world.getTranslateY() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);

        return new Coordinate(chunkX,chunkY);
    }

    private Size getAmountOfLoadedChunks()
    {
        int loadedChunksX = (int) Math.ceil(world.getScene().getWidth() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;
        int loadedChunksY = (int) Math.ceil(world.getScene().getHeight() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;

        return new Size(loadedChunksX, loadedChunksY);
    }
}
