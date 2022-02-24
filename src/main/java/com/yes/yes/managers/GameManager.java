package com.yes.yes.managers;

import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.world.World;
import javafx.scene.layout.VBox;

public class GameManager {
    VBox root;
    World world;

    public World getWorld() {
        return world;
    }

    public GameManager(VBox root) {
        this.root = root;
    }

    public void setup() {
        world = new World();

        root.getChildren().add(world);
    }
}
