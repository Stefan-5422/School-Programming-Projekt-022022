package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class RotateBehaviour extends Component {
    private final Coordinate rotationDirection;
    private final String receiveDataKey;
    private final String offerDataKey;
    private final int processingDuration;
    private int progress = 0;

    public RotateBehaviour(Entity entity, BlockContainer blockContainer, Coordinate rotationDirection, String receiveDataKey, String offerDataKey, int processingDuration) {
        super(entity, blockContainer);
        this.rotationDirection = rotationDirection;
        this.receiveDataKey = receiveDataKey;
        this.offerDataKey = offerDataKey;
        this.processingDuration = processingDuration;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        if (progress > processingDuration) {
            if (parent.getData(offerDataKey) != null) return;

            Item item = parent.getData(receiveDataKey);
            if (rotationDirection.equals(Direction.RIGHT)) {
                item.rotate(1);
            } else if (rotationDirection.equals(Direction.LEFT)) {
                item.rotate(3);
            }

            parent.setData(offerDataKey, item);
            parent.setData(receiveDataKey, null);

            progress = 0;
        } else if (parent.getData(receiveDataKey) != null) {
            progress++;
        }
    }

    @Override
    public void destroy() {

    }
}
