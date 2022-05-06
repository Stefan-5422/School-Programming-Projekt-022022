package com.yes.yes.utils;

import java.security.InvalidParameterException;

public class Direction {
    private Direction() {}

    public static final Coordinate UP = new Coordinate(0, -1);
    public static final Coordinate RIGHT = new Coordinate(1, 0);
    public static final Coordinate DOWN = new Coordinate(0, 1);
    public static final Coordinate LEFT = new Coordinate(-1, 0);

    public static final Coordinate rotateRight(Coordinate direction) {
        if (direction == Direction.LEFT) return Direction.UP;
        if (direction == Direction.UP) return Direction.RIGHT;
        if (direction == Direction.RIGHT) return Direction.DOWN;
        if (direction == Direction.DOWN) return Direction.LEFT;

        throw new InvalidParameterException("Coordinate is not a direction");
    }

    public static final Coordinate rotateLeft(Coordinate direction) {
        if (direction == Direction.LEFT) return Direction.DOWN;
        if (direction == Direction.DOWN) return Direction.RIGHT;
        if (direction == Direction.RIGHT) return Direction.UP;
        if (direction == Direction.UP) return Direction.LEFT;

        throw new InvalidParameterException("Coordinate is not a direction");
    }
}