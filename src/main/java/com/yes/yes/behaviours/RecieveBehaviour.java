package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class RecieveBehaviour extends Component {

    private Coordinate direction;
    private String dataKey;

    private Entity offerer;

    public RecieveBehaviour(Entity entity, BlockContainer blockContainer, Coordinate direction, String dataKey) {
        super(entity, blockContainer);
        this.direction = direction;
        this.dataKey = dataKey;
    }


    private void placed(Entity entity) {
        System.out.println("Entity placed in range");
        try {
            if (blockContainer.getBlockRelative(direction, parent.getRotation()) == entity) {
                System.out.println("Success");
                this.offerer = entity;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            this.offerer = blockContainer.getBlockRelative(direction, parent.getRotation());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.parent.addListener("offerItem", this::receive);
        this.parent.addListener("placed", this::placed);
    }

    void receive(Item item) {
        if (offerer == null) return;
        if(this.parent.getData(dataKey) != null) return;

        this.parent.setData(dataKey, item);
        this.parent.trigger(dataKey + "Changed", item);
        this.offerer.trigger("itemAccepted", this.parent);
        System.out.println(this.parent.getClass().getSimpleName() + "> Received item");
    }

    @Override
    public void update() {

    }

}
