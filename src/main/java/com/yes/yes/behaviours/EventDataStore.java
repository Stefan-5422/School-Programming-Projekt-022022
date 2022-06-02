package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class EventDataStore extends Component {

    private final String eventName;
    private final String dataKey;
    private final boolean global;

    public EventDataStore(Entity entity, BlockContainer blockContainer, String eventName, String dataKey, boolean global) {
        super(entity, blockContainer);
        this.eventName = eventName;
        this.dataKey = dataKey;
        this.global = global;
    }

    private void setData(Item item) {
        this.parent.setData(dataKey, item);
    }

    @Override
    public void initialize() {
        if (global) {
            GlobalEventHandler.addListener(this.eventName, this, this::setData);
        } else {
            this.parent.addListener(this.eventName, this, this::setData);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        if (global) {
            GlobalEventHandler.removeListener(this.eventName, this);
        } else {
            this.parent.removeListener(this.eventName, this);
        }
    }
}
