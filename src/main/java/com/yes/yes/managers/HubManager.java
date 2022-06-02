package com.yes.yes.managers;

import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.GlobalEventHandler;
import com.yes.yes.utils.Item;
import javafx.scene.paint.Color;

public class HubManager {

    private int level = 0;
    private int objectiveCompletion = 0;
    private int objectiveTotal = 0;
    private Item objective = null;

    public void initialize() {
        GlobalEventHandler.addListener("hub:objectiveCompletion", this, this::objectiveCompletion);
        newLevel();
        GlobalEventHandler.trigger("hub:statusText", getStatusText());
    }

    private void newLevel() {
        level++;

        objective = generateObjective();
        GlobalEventHandler.trigger("hub:newObjective", objective);

        objectiveCompletion = 0;
        objectiveTotal = level * 10;

        System.out.println("New level: " + level);
    }


    private Item generateObjective() {
        Item item = new Item();

        int layerCount = (level / 10) + 1;
        int complexity = level % 10; //TODO: Make complexity make stuff more complex

        for (int l = 0; l < layerCount && l < 4; l++) {
            for (int i = 0; i < 4; i++) {
                item.setPart(l, i, new Square(Color.RED));
                item.setPart(0, i, new Square(Color.GREEN));
            }
        }
        return item;
    }

    private String getStatusText() {
        return String.format("Level: %s\n%s/%s", level, objectiveCompletion, objectiveTotal);
    }

    private void objectiveCompletion(Item item) {
        if (item == null) return;
        if (item.equals(objective)) {
            objectiveCompletion++;
        }
        if (objectiveCompletion >= objectiveTotal) {
            newLevel();
        }

        GlobalEventHandler.trigger("hub:statusText", getStatusText());

    }
}
