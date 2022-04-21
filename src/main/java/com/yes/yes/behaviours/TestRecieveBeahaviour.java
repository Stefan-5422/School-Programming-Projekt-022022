package com.yes.yes.behaviours;

import com.yes.yes.utils.*;

public class TestRecieveBeahaviour extends Component {

        public TestRecieveBeahaviour(Entity entity, BlockContainer blockContainer) {
            super(entity, blockContainer);
        }


        @Override
        public void initialize() {
            this.parent.addListener("offerItem", this::recieve);
        }

        void recieve(Item item) {
            System.out.println("Received Item");
        }

    @Override
    public void update() {

    }
}
