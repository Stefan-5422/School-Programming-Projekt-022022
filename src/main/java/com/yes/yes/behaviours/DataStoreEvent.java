package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class DataStoreEvent extends Component {

    private final String dataKey;
    private final String eventName;
    private final boolean global;


    public DataStoreEvent(Entity entity, BlockContainer blockContainer, String dataKey, String eventName, boolean global) {
        super(entity, blockContainer);
        this.dataKey = dataKey;
        this.eventName = eventName;
        this.global = global;
    }

    private void triggerEvent(Item item) {
        if (global) {
            GlobalEventHandler.trigger(eventName, item);
        } else {
            this.parent.trigger(eventName, item);
        }
    }

    @Override
    public void initialize() {
        this.parent.addListener(dataKey + "Changed", this, this::triggerEvent);
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        if (global) {
            GlobalEventHandler.removeListener(eventName, this);
        } else {
            this.parent.removeListener(dataKey + "Changed", this);
        }
    }
}
