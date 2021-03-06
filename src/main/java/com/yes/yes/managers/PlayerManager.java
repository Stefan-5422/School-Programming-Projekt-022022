package com.yes.yes.managers;

import com.yes.yes.ui.BuildBox;
import com.yes.yes.utils.*;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.Scene;
import javafx.scene.input.*;

public class PlayerManager {

    private static final KeyCombination LEFT_KEY = new KeyCodeCombination(KeyCode.A);
    private static final KeyCombination RIGHT_KEY = new KeyCodeCombination(KeyCode.D);
    private static final KeyCombination UP_KEY = new KeyCodeCombination(KeyCode.W);
    private static final KeyCombination DOWN_KEY = new KeyCodeCombination(KeyCode.S);
    private static final KeyCombination DELETE_KEY = new KeyCodeCombination(KeyCode.X);
    private static final KeyCombination HOME_KEY = new KeyCodeCombination(KeyCode.HOME);

    private final World world;
    private final BuildBox buildBox;
    private Coordinate chunkPos = new Coordinate(0, 0);
    private int currentRotation = 0;
    private Coordinate currentMousePosition;

    public PlayerManager(World world, BuildBox buildBox) {
        this.world = world;
        this.buildBox = buildBox;
    }

    public void initialize() {
        Scene scene = world.getScene();
        scene.setOnKeyPressed(this::processKeyPress);
        scene.setOnKeyReleased(this::processKeyRelease);

        scene.widthProperty().addListener((e) -> redrawChunks());
        scene.heightProperty().addListener((e) -> redrawChunks());

        world.setOnMouseClicked(this::processClick);
        world.setTranslateX(GameManager.START_POSITION.x + world.getScene().getWidth() / 2 + 100);
        world.setTranslateY(GameManager.START_POSITION.y + world.getScene().getHeight() / 2 + 100);
        world.setOnMouseMoved((mouse) -> currentMousePosition = new Coordinate((int) mouse.getX(), (int) mouse.getY()));

        redrawChunks();
    }

    private void processClick(MouseEvent mouse) {
        Coordinate mouseCoordinate = new Coordinate((int) mouse.getX(), (int) mouse.getY());
        Coordinate chunkCoordinate = Coordinate.worldToChunkCoordinate(mouseCoordinate);
        Coordinate blockCoordinate = Coordinate.worldToChunkBlock(mouseCoordinate);


        try {
            Class<?>[] types = new Class<?>[1];
            types[0] = BlockContainer.class;

            Chunk chunk = world.getChunk(chunkCoordinate);
            Entity oldEntity = chunk.getEntity(blockCoordinate);

            BlockContainer blockContainer = new BlockContainer(world, chunk.getChunkColor(), blockCoordinate, chunkCoordinate);
            RegistryEntry registryEntry = EntityRegistry.getEntry(buildBox.getSelectedEntity());

            if (registryEntry == null)
                return;

            Entity entity = (Entity) registryEntry.getEntity().getConstructor(types).newInstance(blockContainer);

            if (oldEntity != null && oldEntity.getClass().getSimpleName().equals(entity.getClass().getSimpleName())) {
                if (mouse.getButton() == MouseButton.PRIMARY)
                    currentRotation = oldEntity.getRotation() + 1;
                else
                    currentRotation = oldEntity.getRotation() - 1;
            }

            entity.setRotation(currentRotation);

            chunk.setEntity(entity, blockCoordinate);

            entity.initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processKeyRelease(KeyEvent key) {
        if (HOME_KEY.match(key)) {
            world.setTranslateX(GameManager.START_POSITION.x + world.getScene().getWidth() / 2 + 100);
            world.setTranslateY(GameManager.START_POSITION.y + world.getScene().getHeight() / 2 + 100);
            redrawChunks();
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
        if (DELETE_KEY.match(key)) {
            Coordinate chunkCoordinate = Coordinate.worldToChunkCoordinate(currentMousePosition);
            Coordinate blockCoordinate = Coordinate.worldToChunkBlock(currentMousePosition);

            Chunk chunk = world.getChunk(chunkCoordinate);
            chunk.removeEntity(blockCoordinate);
        }

        Size preferredAmountOfChunks = getPreferredAmountOfChunks();
        Coordinate chunkPosition = Coordinate.sceneToChunkCoordinate(new Coordinate((int) world.getTranslateX() + 100, (int) world.getTranslateY() + 100));

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
        Coordinate chunkPosition = Coordinate.sceneToChunkCoordinate(new Coordinate((int) world.getTranslateX() + 100, (int) world.getTranslateY() + 100));

        world.unloadAllChunks();

        for (int y = 0; y < preferredAmountOfChunks.y; y++) {
            for (int x = 0; x < preferredAmountOfChunks.x; x++) {
                world.loadChunk(new Coordinate(chunkPosition.x + x, chunkPosition.y + y));
            }
        }
    }

    private Size getPreferredAmountOfChunks() {
        int loadedChunksX = (int) Math.ceil(world.getScene().getWidth() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;
        int loadedChunksY = (int) Math.ceil(world.getScene().getHeight() / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE) + 1;

        return new Size(loadedChunksX, loadedChunksY);
    }
}
