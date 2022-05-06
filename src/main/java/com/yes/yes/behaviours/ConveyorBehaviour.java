package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;

public class ConveyorBehaviour extends Component {
    private final String receiveDataKey;
    private final String offerDataKey;
    private final int delay;
    int count = 0;

    boolean isConveyed = false;

    public ConveyorBehaviour(Entity entity, BlockContainer blockContainer, String receiveDataKey, String offerDataKey, int delay) {
        super(entity, blockContainer);
        this.receiveDataKey = receiveDataKey;
        this.offerDataKey = offerDataKey;
        this.delay = delay;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        if (count >= delay) {
            if (parent.getData(offerDataKey) != null) return;
            if (parent.getData(receiveDataKey) == null) return;

            parent.setData(offerDataKey, parent.getData(receiveDataKey));
            count = 0;
            isConveyed = true;
        } else if (parent.getData(receiveDataKey) != null && !isConveyed) {
            count++;
        }

        if(isConveyed && parent.getData(offerDataKey) == null)
        {
            parent.setData(receiveDataKey, null);
            isConveyed = false;
        }
    }
}
