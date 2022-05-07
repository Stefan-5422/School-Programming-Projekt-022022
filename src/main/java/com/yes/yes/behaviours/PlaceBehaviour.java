package com.yes.yes.behaviours;

import com.yes.yes.entities.parts.Square;
import com.yes.yes.utils.*;

public class PlaceBehaviour extends Component {

    public PlaceBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    @Override
    public void initialize() {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                try {
                    Entity entity = blockContainer.getBlockAbsolute(new Coordinate(x, y));

                    if (entity != null) {
                        entity.trigger("placed", parent);
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

    @Override
    public void destroy() {

    }
}
