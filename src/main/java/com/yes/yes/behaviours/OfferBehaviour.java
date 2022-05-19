package com.yes.yes.behaviours;

import com.yes.yes.utils.*;
import javafx.util.Pair;

public class OfferBehaviour extends Component {

    private final Coordinate direction;
    private final String dataKey;


    private Entity receiver;


    public OfferBehaviour(Entity entity, BlockContainer blockContainer, Coordinate direction, String dataKey) {
        super(entity, blockContainer);

        this.direction = direction;
        this.dataKey = dataKey;
    }

    private void placed(Entity entity) {
        //System.out.println("Entity placed in range");
        try {
            if (blockContainer.getBlockRelative(direction, parent.getRotation()) == entity) {
                this.receiver = entity;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void itemAccepted(Entity entity) {
        try {
            if (blockContainer.getBlockRelative(direction, parent.getRotation()) == entity) {
                this.parent.setData(dataKey, null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {
            this.receiver = blockContainer.getBlockRelative(direction, parent.getRotation());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.parent.addListener("itemAccepted", this, this::itemAccepted);
        this.parent.addListener("placed", this, this::placed);
    }

    @Override
    public void update() {
        if (receiver == null) return;

        Item item = this.parent.getData(dataKey);
        if (item == null) return;

        this.receiver.trigger("offerItem", new Pair<>(parent, item));
    }

    @Override
    public void destroy() {
        this.parent.removeListener("itemAccepted", this);
        this.parent.removeListener("placed", this);

        receiver = null;
    }
}
