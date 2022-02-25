package com.yes.yes.managers;

import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class PlayerManager {

    final KeyCombination leftKey = new KeyCodeCombination(KeyCode.A);
    final KeyCombination rightKey = new KeyCodeCombination(KeyCode.D);
    final KeyCombination upKey = new KeyCodeCombination(KeyCode.W);
    final KeyCombination downKey = new KeyCodeCombination(KeyCode.S);

    World world;
    Coordinate chunkPos = new Coordinate(0, 0);

    public PlayerManager(World world) {
        this.world = world;
    }

    public void initialize() {
        Scene scene = world.getScene();
        scene.setOnKeyPressed(this::ProcessKeyPress);

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

        double offset = +Chunk.CHUNK_SIZE * Chunk.ENTITY_SIZE;


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
