package com.yes.yes.entities.machines;

import com.yes.yes.behaviours.TestBehaviour;
import com.yes.yes.utils.Coordinate;
import com.yes.yes.utils.Entity;
import com.yes.yes.utils.EntityRegistry;
import com.yes.yes.utils.RegistryEntry;
import com.yes.yes.utils.exceptions.AlreadyExistsException;
import javafx.scene.shape.Circle;

public class TestMachine extends Entity {

    public TestMachine() {
        super();
        this.getChildren().add(new Circle(25));

        this.addBehaviour(new TestBehaviour(this));
    }
}
