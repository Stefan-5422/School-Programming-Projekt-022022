package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class CutItemBehaviour extends Component {
    private final Orientation orientation;
    private final String receiveDataKey;
    private final String offerDataKey1;
    private final String offerDataKey2;
    private final int processingDuration;
    private int progress = 0;

    public CutItemBehaviour(Entity entity, BlockContainer blockContainer, Orientation orientation, String receiveDataKey, String offerDataKey1, String offerDataKey2, int processingDuration) {
        super(entity, blockContainer);
        this.orientation = orientation;
        this.receiveDataKey = receiveDataKey;
        this.offerDataKey1 = offerDataKey1;
        this.offerDataKey2 = offerDataKey2;
        this.processingDuration = processingDuration;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        if (progress > processingDuration) {
            if (parent.getData(offerDataKey1) != null || parent.getData(offerDataKey2) != null) return;


            Item half1 = new Item();
            Item half2 = new Item();

            Item item = parent.getData(receiveDataKey);
            for (int i = 0; i < 4; i++) {
                if (orientation == Orientation.Horizontal) {
                    half1.setPart(i, 0, item.getPart(i, 0));
                    half1.setPart(i, 1, item.getPart(i, 1));
                    half2.setPart(i, 2, item.getPart(i, 2));
                    half2.setPart(i, 3, item.getPart(i, 3));
                } else {
                    half1.setPart(i, 0, item.getPart(i, 0));
                    half1.setPart(i, 2, item.getPart(i, 2));
                    half2.setPart(i, 1, item.getPart(i, 1));
                    half2.setPart(i, 3, item.getPart(i, 3));
                }
            }

            parent.setData(offerDataKey1, half1);
            parent.setData(offerDataKey2, half2);
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
