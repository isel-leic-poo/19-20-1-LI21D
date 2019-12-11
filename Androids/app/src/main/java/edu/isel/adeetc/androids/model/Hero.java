package edu.isel.adeetc.androids.model;

/**
 * Represents the hero in the Robots game.
 */
public class Hero extends Actor {

    private final int arenaWidth;
    private final int arenaHeight;

    /**
     * Initiates the hero at the origin
     * @param arenaWidth    The arena width
     * @param arenaHeight   The arena height
     */
    public Hero(int arenaWidth, int arenaHeight) {
        this(0,0, arenaWidth, arenaHeight);
    }

    /**
     * Initiates the hero at the given coordinates
     * @param x             The vertical coordinate
     * @param y             The horizontal coordinate
     * @param arenaWidth    The arena width
     * @param arenaHeight   The arena height
     */
    public Hero(int x, int y, int arenaWidth, int arenaHeight) {
        super(x, y);
        this.arenaWidth = arenaWidth;
        this.arenaHeight = arenaHeight;
    }

    /**
     * Moves the hero by the given distance
     * @param dX the horizontal distance
     * @param dY the vertical distance
     * @return the new hero position
     */
    public Coordinate moveBy(int dX, int dY) {
        Coordinate newPosition = new Coordinate(position.x + dX, position.y + dY);
        if (verifyBounds(newPosition)) {
            position = newPosition;
        }
        return position;
    }

    /**
     * Helper method used to verify if the given coordinate is within the arena bounds.
     * @param newPosition   The coordinate to be verified
     * @return  A boolean value indicating whether the position is valid or not
     */
    private boolean verifyBounds(Coordinate newPosition) {
        return newPosition.x >= 0 && newPosition.x < arenaWidth &&
            newPosition.y >= 0 && newPosition.y < arenaHeight;
    }
}
