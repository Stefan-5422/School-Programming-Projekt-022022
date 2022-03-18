package com.yes.yes.managers;

import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.entities.machines.TestMachine2;
import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.utils.RegistryEntry;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import com.yes.yes.world.World;
import javafx.scene.layout.VBox;

public class GameManager {
    private final VBox root;
    private World world;

    public GameManager(VBox root) {
        this.root = root;
    }

    public World getWorld() {
        return world;
    }

    public void initialize() {
        world = new World();

        root.getChildren().add(world);
        world.toBack();

        try {
            EntityRegistry.register(new RegistryEntry("test", "test machine", TestMachine.class));
            EntityRegistry.register(new RegistryEntry("test2", "test machine", TestMachine2.class));
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
