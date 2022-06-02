package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;

public class DestroyBehaviour extends Component {
    private final String dataKey;

    public DestroyBehaviour(Entity entity, BlockContainer blockContainer, String dataKey) {
        super(entity, blockContainer);
        this.dataKey = dataKey;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        if(this.parent.getData(dataKey) !=null) {
            this.parent.setData(dataKey, null);
        }
    }

    @Override
    public void destroy() {

    }
}
