package model;

/**
 * Class that captures commonalities between all the game's actors.
 */
public abstract class Actor {

    /**
     * The actor current position
     */
    protected Coordinate position;

    /**
     * Initiates the actor at the given coordinates
     * @param x The vertical coordinate
     * @param y The horizontal coordinate
     */
    public Actor(int x, int y) {
        this(new Coordinate(x, y));
    }

    /**
     * Initiates the actor at the given coordinates
     * @param position  The initial coordinates
     */
    public Actor(Coordinate position) {
        this.position = position;
    }

    /**
     * Gets the actor current position
     * @return  the actor position
     */
    public Coordinate getPosition() {
        return position;
    }
}
