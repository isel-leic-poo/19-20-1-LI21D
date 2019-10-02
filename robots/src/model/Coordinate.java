package model;

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
}
