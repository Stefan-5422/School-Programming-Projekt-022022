package com.yes.yes.behaviours;

import com.yes.yes.utils.Component;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.EntityRegistry;

public class TestBehaviour extends Component {

    public TestBehaviour(Entity entity) {
        super(entity);
    }

    @Override
    public void initialize() {
        System.out.println(EntityRegistry.getEntity("test").toString());
    }

    @Override
    public void update() {
        
    }
}
