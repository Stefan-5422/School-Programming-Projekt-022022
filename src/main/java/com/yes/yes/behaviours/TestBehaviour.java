package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;

public class TestBehaviour extends Component {

    public TestBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
        System.out.println("Updating " + parent.getClass().getSimpleName());
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                try {
                    System.out.println("Offset: X:" + x + " Y:" + y);
                    Entity entity = blockContainer.getBlock(new Coordinate(x, y));
                    if (entity != null)
                        System.out.println(entity.getClass().getSimpleName());
                } catch (IllegalAccessException ex) {
                   ex.printStackTrace();
                }
            }
        }
    }
}
