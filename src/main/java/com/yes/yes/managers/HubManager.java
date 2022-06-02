package com.yes.yes.managers;

import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.GlobalEventHandler;
import com.yes.yes.utils.Item;
import com.yes.yes.utils.NoiseGenerator;
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

        NoiseGenerator n;

        Item item = new Item();

        int layerCount = (level / 10) + 1;
        int complexity = (level % 10) + Math.min(layerCount-1,3) * 3;

        for (int l = 0; l < layerCount && l < 4; l++) { //Find a way to balance this better
            int color = 0;
            for (int i = 0; i < 4; i++) {
                double noise = NoiseGenerator.calculate(complexity*level+layerCount+(i+1)*(l+1))/1e8;
                System.out.println(noise);
                if(complexity >= 3 && complexity < 5){
                    if(noise > 10) {
                        i++;
                        continue;
                    }
                }else if(complexity > 5) {
                    if(noise > 15-layerCount) {
                        continue;
                    }
                }
                if(complexity > 1 && complexity < 4 && (i == 0 || i == 2)){
                    color = ((int)(noise/1.5))%3;
                }
                if(complexity > 4){
                    color = ((int)(noise/1.5))%3;
                }
                Color c = (color == 0)? Color.RED : (color==1)? Color.GREEN : Color.BLUE;
                item.setPart(l, i, new Square(c));
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
