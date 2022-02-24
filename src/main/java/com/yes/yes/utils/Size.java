package com.yes.yes.utils;

import java.util.Objects;

public class Size {
    public int x;
    public int y;

    public Size(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Size() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
