package com.yes.yes.behaviours;

import com.yes.yes.utils.*;
import javafx.util.Pair;

public class ReceiveBehaviour extends Component {

    private final Coordinate direction;
    private final String dataKey;

    private Entity offerer;

    public ReceiveBehaviour(Entity entity, BlockContainer blockContainer, Coordinate direction, String dataKey) {
        super(entity, blockContainer);
        this.direction = direction;
        this.dataKey = dataKey;
    }


    private void placed(Entity entity) {
        try {
            if (blockContainer.getBlockRelative(direction, parent.getRotation()) == entity) {
                this.offerer = entity;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            this.offerer = blockContainer.getBlockRelative(direction, parent.getRotation());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        this.parent.addListener("offerItem", this, this::receive);
        this.parent.addListener("placed", this, this::placed);
    }

    void receive(Pair<Entity,Item> pair) {
        if (offerer == null || offerer != pair.getKey()) return;

        if (this.parent.getData(dataKey) == pair.getValue())
            this.offerer.trigger("itemAccepted", this.parent);

        if (this.parent.getData(dataKey) != null) return;

        this.parent.setData(dataKey, pair.getValue());
        this.parent.trigger(dataKey + "Changed", pair.getValue());
        this.offerer.trigger("itemAccepted", this.parent);
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        this.parent.removeListener("offerItem", this);
        this.parent.removeListener("placed", this);

        offerer = null;
    }

}
