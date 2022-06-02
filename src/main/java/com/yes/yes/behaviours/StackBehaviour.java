package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class StackBehaviour extends Component {
    private final int processingDuration;
    private final String receiveDataKey1;
    private final String receiveDataKey2;
    private final String offerDataKey;
    private int progress = 0;


    public StackBehaviour(Entity entity, BlockContainer blockContainer, String receiveDataKey1, String receiveDataKey2, String offerDataKey, int processingDuration) {
        super(entity, blockContainer);
        this.receiveDataKey1 = receiveDataKey1;
        this.receiveDataKey2 = receiveDataKey2;
        this.offerDataKey = offerDataKey;
        this.processingDuration = processingDuration;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        GlobalExecQueue.schedule(() -> {
            if (progress > processingDuration) {
                if (parent.getData(offerDataKey) != null) return;

                Item item1 = parent.getData(receiveDataKey1);
                Item item2 = parent.getData(receiveDataKey2);

                for (int quarter = 0; quarter < 4; quarter++) {
                    int offset = 0;
                    for (int layer = 0; layer < 4; layer++) {
                        if (item1.getPart(layer, quarter) != null) {
                            offset++;
                            continue;
                        }
                        item1.setPart(layer, quarter, item2.getPart(layer - offset, quarter));
                    }
                }

                parent.setData(offerDataKey, item1);
                parent.setData(receiveDataKey1, null);
                parent.setData(receiveDataKey2, null);

            } else if (parent.getData(receiveDataKey1) != null && parent.getData(receiveDataKey2) != null) {
                progress++;
            }
        });
    }

    @Override
    public void destroy() {

    }
}
