package com.yes.yes.managers;

import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.world.World;
import javafx.scene.layout.VBox;

public class GameManager {
    VBox root;

    public GameManager(VBox root) {
        this.root = root;
    }

    public void setup() {
        World world = new World();

        root.getChildren().add(world);

        world.getChunk(new Coordinate(1, 1)).setEntity(new TestMachine(), new Coordinate(1, 1));
        world.getChunk(new Coordinate(1, 1)).setEntity(new TestMachine(), new Coordinate(1, 2));
    }
}
