package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;

public class PlaceBehaviour extends Component {

    public PlaceBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    @Override
    public void initialize()
    {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                try {
                    Entity entity = blockContainer.getBlock(new Coordinate(x, y));

                    if (entity != null) {
                        entity.trigger("placed", parent);
                        System.out.println(entity.getClass().getSimpleName());
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {

    }
}
