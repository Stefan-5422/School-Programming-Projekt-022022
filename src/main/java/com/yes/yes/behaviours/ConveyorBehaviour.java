package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;

public class ConveyorBehaviour extends Component {
    private final String receiveDataKey;
    private final String offerDataKey;
    private final int delay;
    int delayCount = 0;
    boolean isConveyed = false;

    public ConveyorBehaviour(Entity entity, BlockContainer blockContainer, int delay, String receiveDataKey, String offerDataKey) {
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
        if (delayCount > delay) {
            delayCount = 0;

            if (parent.getData(offerDataKey) != null) return;

            parent.setData(offerDataKey, parent.getData(receiveDataKey));
            isConveyed = true;
        } else if (parent.getData(receiveDataKey) != null && !isConveyed) {
            delayCount++;
        }

        if (isConveyed && parent.getData(offerDataKey) == null) {
            parent.setData(receiveDataKey, null);
            isConveyed = false;
        }
    }

    @Override
    public void destroy() {

    }
}
