package com.yes.yes.managers;

import com.yes.yes.entities.machines.*;
import com.yes.yes.utils.*;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import com.yes.yes.world.Chunk;
import com.yes.yes.world.World;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameManager {
    public static final Coordinate START_POSITION = new Coordinate(Integer.MAX_VALUE / -5000, Integer.MAX_VALUE / -5000);

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
            EntityRegistry.register(new RegistryEntry("destroyer", "Incinerator", DestroyMachine.class));
            EntityRegistry.register(new RegistryEntry("generator", "Generator", GeneratorMachine.class));
            EntityRegistry.register(new RegistryEntry("conveyorBelt", "Conveyor Belt", ConveyorBelt.class));
            EntityRegistry.register(new RegistryEntry("splitter", "Splitter", Splitter.class));
            EntityRegistry.register(new RegistryEntry("cutter", "Cutter", Cutter.class));
            EntityRegistry.register(new RegistryEntry("stacker", "Stacker", Stacker.class));
            EntityRegistry.register(new RegistryEntry("rotator", "Rotator", Rotator.class));
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                GlobalEventHandler.trigger("global:timerTick", null);
                GlobalExecQueue.run();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //System.out.println("Finished tick!");
        }, 0, 100, TimeUnit.MILLISECONDS);

        GlobalEventHandler.addListener("app:closing", this, (__) -> executor.shutdown());

        generateHub();
    }

    public void generateHub() {
        Coordinate startChunk = Coordinate.sceneToChunkCoordinate(START_POSITION);
        Coordinate centerPosition = new Coordinate((int) Math.ceil(Chunk.CHUNK_SIZE / 2f), (int) Math.ceil(Chunk.CHUNK_SIZE / 2f));

        Chunk chunk = world.getChunk(startChunk);

        for (int x = centerPosition.x - 2; x <= centerPosition.x; x++) {
            for (int y = centerPosition.y - 2; y <= centerPosition.y; y++) {
                if (x == centerPosition.x - 1 && y == centerPosition.y - 1) {
                    BlockContainer blockContainer = new BlockContainer(world, chunk.getChunkColor(), new Coordinate(x, y), startChunk);
                    HubDisplay center = new HubDisplay(blockContainer);
                    chunk.setEntity(center, new Coordinate(x, y));
                    center.initialize();
                    continue;
                }
                BlockContainer blockContainer = new BlockContainer(world, chunk.getChunkColor(), new Coordinate(x, y), startChunk);
                HubAcceptor hubAcceptor = new HubAcceptor(blockContainer);
                chunk.setEntity(hubAcceptor, new Coordinate(x, y));
                hubAcceptor.initialize();
            }
        }

    }
}
