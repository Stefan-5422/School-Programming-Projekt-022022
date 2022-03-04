package com.yes.yes.managers;

import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.ui.BuildBox;
import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerManager {

    final KeyCombination leftKey = new KeyCodeCombination(KeyCode.A);
    final KeyCombination rightKey = new KeyCodeCombination(KeyCode.D);
    final KeyCombination upKey = new KeyCodeCombination(KeyCode.W);
    final KeyCombination downKey = new KeyCodeCombination(KeyCode.S);

    World world;
    BuildBox buildBox;
    Coordinate chunkPos = new Coordinate(0, 0);

    public PlayerManager(World world, BuildBox buildBox) {
        this.world = world;
        this.buildBox = buildBox;
    }

    public void initialize() {
        Scene scene = world.getScene();
        scene.setOnKeyPressed(this::ProcessKeyPress);

        world.setOnMouseClicked((e) ->
                {
                    Coordinate mouseCoordinate = new Coordinate((int) e.getX(), (int) e.getY());
                    Coordinate chunkCoordinate = Coordinate.WorldToChunkCoordinate(mouseCoordinate);
                    Coordinate blockCoordinate = Coordinate.WorldToChunkBlock(mouseCoordinate);

                    try {
                        Class[] types = new Class[1];
                        types[0] = BlockContainer.class;

                        BlockContainer blockContainer = new BlockContainer(world, blockCoordinate, chunkCoordinate);
                        Entity entity = (Entity) EntityRegistry.getEntity(buildBox.getSelectedEntity()).getEntity().getConstructor(types).newInstance(blockContainer);

                        world.getChunk(chunkCoordinate).setEntity(entity, blockCoordinate);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        world.setTranslateX(Integer.MAX_VALUE / -5000);
        world.setTranslateY(Integer.MAX_VALUE / -5000);
    }

    void ProcessKeyPress(KeyEvent key) {
        if (leftKey.match(key)) {
            world.setTranslateX(world.getTranslateX() + 45);
        }
        if (rightKey.match(key)) {

            world.setTranslateX(world.getTranslateX() - 45);
        }
        if (upKey.match(key)) {
            world.setTranslateY(world.getTranslateY() + 45);
        }
        if (downKey.match(key)) {
            world.setTranslateY(world.getTranslateY() - 45);
        }

        double offset = Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;


        //NOTE: WHAT???


        int chunkX = -(int) Math.floor((world.getTranslateX() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);
        int chunkY = -(int) Math.floor((world.getTranslateY() + offset) / Chunk.CHUNK_SIZE / Chunk.ENTITY_SIZE);

        if (chunkX < chunkPos.x) {
            for (int i = 0; i < 6; i++) {
                world.load(new Coordinate(chunkX, chunkY + i));
                world.unload(new Coordinate(chunkPos.x + 5, chunkY + i));
            }
        }

        if (chunkX > chunkPos.x) {
            for (int i = 0; i < 6; i++) {
                world.load(new Coordinate(chunkX + 5, chunkY + i));
                world.unload(new Coordinate(chunkPos.x, chunkY + i));
            }
        }

        if (chunkY > chunkPos.y) {
            for (int i = 0; i < 6; i++) {
                world.load(new Coordinate(chunkX + i, chunkY + 5));
                world.unload(new Coordinate(chunkX + i, chunkPos.y));
            }
        }

        if (chunkY < chunkPos.y) {
            for (int i = 0; i < 6; i++) {
                world.load(new Coordinate(chunkX + i, chunkY));
                world.unload(new Coordinate(chunkX + i, chunkPos.y + 5));
            }
        }


        chunkPos = new Coordinate(chunkX, chunkY);
    }
}
