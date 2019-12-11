package edu.isel.adeetc.androids.model;

import java.util.Objects;

/**
 * Class used to represent coordinates on the robots game board.
 */
public class Coordinate {

    /**
     * The horizontal coordinate
     */
    public final int x;

    /**
     * The vertical coordinate
     */
    public final int y;

    /**
     * Initiates an instance with the given values
     * @param x The horizontal coordinate
     * @param y The vertical coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Coordinate that = (Coordinate) other;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
