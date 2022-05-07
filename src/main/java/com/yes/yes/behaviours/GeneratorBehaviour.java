package com.yes.yes.behaviours;

import com.yes.yes.utils.BlockContainer;
import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.Item;

public class GeneratorBehaviour extends Component {

    private Item item;
    private String dataKey;

    public GeneratorBehaviour(Entity entity, BlockContainer blockContainer, Item item, String dataKey) {
        super(entity, blockContainer);
        this.item = item;
        this.dataKey = dataKey;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        this.parent.setData(dataKey, item.clone());
        System.out.println("generate");
    }

    @Override
    public void destroy() {

    }
}
