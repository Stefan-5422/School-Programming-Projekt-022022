package com.yes.yes.managers;

import com.yes.yes.entities.machines.ConveyorBelt;
import com.yes.yes.entities.machines.GeneratorMachine;
import com.yes.yes.entities.machines.TestMachine;
import com.yes.yes.entities.machines.TestMachine2;
import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.utils.GlobalEventHandler;
import com.yes.yes.utils.RegistryEntry;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import com.yes.yes.world.World;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
            EntityRegistry.register(new RegistryEntry("test", "test receive machine", TestMachine.class));
            EntityRegistry.register(new RegistryEntry("test2", "test offer machine", TestMachine2.class));
            EntityRegistry.register(new RegistryEntry("generator", "generator", GeneratorMachine.class));
            EntityRegistry.register(new RegistryEntry("conveyorBelt", "Conveyor Belt", ConveyorBelt.class));
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            try {
                GlobalEventHandler.trigger("timerTick", null);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Finished tick!");
        }, 0, 1, TimeUnit.SECONDS);

        GlobalEventHandler.addListener("timerTick", (__) -> {
            System.out.println("Tick!");
        });
        GlobalEventHandler.addListener("closing", (__) -> executor.shutdown());
    }
}
