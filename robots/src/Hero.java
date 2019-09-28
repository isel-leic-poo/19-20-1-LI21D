/**
 * Represents the hero in the Robots game.
 */
public class Hero {

    /**
     * The hero current position
     */
    private Coordinate position;

    /**
     * Initiates the hero at the given coordinates
     * @param x The vertical coordinate
     * @param y The horizontal coordinate
     */
    public Hero(int x, int y) {
        this(new Coordinate(x, y));
    }

    /**
     * Initiates the hero at the given coordinates
     * @param position  The initial coordinates
     */
    public Hero(Coordinate position) {
        this.position = position;
    }

    /**
     * Gets the hero current position
     * @return  the hero position
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Moves the hero by the given distance
     * @param dX the horizontal distance
     * @param dY the vertical distance
     * @return the new hero position
     */
    public Coordinate moveBy(int dX, int dY) {
        position = new Coordinate(position.x + dX, position.y + dY);
        return position;
    }
}
