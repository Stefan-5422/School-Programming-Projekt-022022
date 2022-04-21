package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class TestOffererBehaviour extends Component {

    private Entity receiver;

    public TestOffererBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    private void placed(Entity entity) {
        try {
            if (blockContainer.getBlock(new Coordinate(1, 0)) == entity) {
                this.receiver = entity;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            this.receiver = blockContainer.getBlock(new Coordinate(1, 0));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.parent.addListener("placed", this::placed);
    }

    @Override
    public void update() {
        if(receiver == null) return;

        Item someItem = new Item();

        receiver.trigger("offerItem", someItem);
    }
}
