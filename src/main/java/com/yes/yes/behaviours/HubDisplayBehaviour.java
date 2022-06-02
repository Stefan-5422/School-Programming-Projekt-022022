package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.GlobalEventHandler;
import javafx.scene.text.Text;

public class HubDisplayBehaviour extends Component {

    private final String eventName;
    private final Text text;

    public HubDisplayBehaviour(Entity entity, BlockContainer blockContainer, String eventName, Text text) {
        super(entity, blockContainer);
        this.eventName = eventName;
        this.text = text;
    }

    private void updateText(String content) {
        this.text.setText(content);
    }

    @Override
    public void initialize() {
        GlobalEventHandler.addListener(eventName, this, this::updateText);
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        GlobalEventHandler.removeListener(eventName, this);
    }
}
