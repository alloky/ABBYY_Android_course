package com.github.alloky;

import java.util.Objects;

public class Point {
    public int x, y;

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Point p = (Point) other;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int myHashCode() {
        return x + 31 * y;
    }

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
