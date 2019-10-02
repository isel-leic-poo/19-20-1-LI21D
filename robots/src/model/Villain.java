package model;

/**
 * Represents the villains in the Robots game.
 */
public class Villain extends Actor {

    /**
     * Initiates the villain at the given coordinates
     * @param x The vertical coordinate
     * @param y The horizontal coordinate
     */
    public Villain(int x, int y) {
        super(x, y);
    }

    /**
     * Moves the villain towards the hero.
     * @return the new position
     */
    public Coordinate moveTowards(Coordinate destination) {
        // Compute deltas
        int dx = destination.x - position.x;
        dx = dx == 0 ? dx : (dx > 0 ? 1 : -1);
        int dy = destination.y - position.y;
        dy = dy == 0 ? dy : (dy > 0 ? 1 : -1);

        position = new Coordinate(position.x + dx, position.y + dy);
        return position;
    }

}
