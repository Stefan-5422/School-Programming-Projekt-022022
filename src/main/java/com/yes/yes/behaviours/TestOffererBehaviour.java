package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;

public class TestOffererBehaviour extends Component {

    private Entity reciever;

    public TestOffererBehaviour(Entity entity, BlockContainer blockContainer) {
        super(entity, blockContainer);
    }

    private void placed(Entity entity) {
        try {
            if (blockContainer.getBlock(new Coordinate(1, 0)) == entity) {
                this.reciever = entity;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            this.reciever = blockContainer.getBlock(new Coordinate(1, 0));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.parent.addListener("placed", this::placed);
    }

    @Override
    public void update() {
        if(reciever == null) return;

        reciever.trigger("offerItem", someItem);
    }
}
