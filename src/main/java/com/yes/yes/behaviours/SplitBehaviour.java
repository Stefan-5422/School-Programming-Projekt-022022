package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;

public class SplitBehaviour extends Component {

    private final int delay;
    private final String receiveDataKey;
    private final String[] offerDataKeys;
    private int delayCount = 0;
    private int offerDataKeyIndex = 0;

    public SplitBehaviour(Entity entity, BlockContainer blockContainer, int delay, String receiveDataKey, String... offerDataKeys) {
        super(entity, blockContainer);
        this.delay = delay;
        this.receiveDataKey = receiveDataKey;
        this.offerDataKeys = offerDataKeys;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        if (delayCount > delay) {


            offerDataKeyIndex++;
            if (offerDataKeyIndex >= offerDataKeys.length)
                offerDataKeyIndex = 0;

            String offerDataKey = offerDataKeys[offerDataKeyIndex];

            if (parent.getData(offerDataKey) != null) return;

            parent.setData(offerDataKey, parent.getData(receiveDataKey));
            parent.setData(receiveDataKey, null);

            delayCount = 0;
        } else if (parent.getData(receiveDataKey) != null) {
            delayCount++;
        }
    }

    @Override
    public void destroy() {

    }
}
