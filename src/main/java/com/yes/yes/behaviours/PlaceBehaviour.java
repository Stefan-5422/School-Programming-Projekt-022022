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
                    Entity entity = blockContainer.getBlock(new Coordinate(x, y));

                    if (entity != null) {
                        entity.trigger("placed", parent);
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        Item item = new Item();
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                item.setPart(i, i1, new Square());
            }
        }
        //  parent.getChildren().add(item);
    }

    @Override
    public void update() {

    }
}
