package hr.dorescanin.util;

import java.util.Objects;

/**
 * Immutable class for representing an (x, y) pair
 */
public class CoordinatePair {

    private int x, y;

    public CoordinatePair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePair that = (CoordinatePair) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CoordinatePair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
