package model;

/**
 * Represents the Robots game state
 */
public class Game {

    public final Hero hero;
    public final Villain villain;

    /**
     * Initiates the game with the given arena dimensions
     * @param width     The arena width
     * @param height    The arena height
     */
    public Game(int width, int height) {
        hero = new Hero(20, 10, width, height);
        villain = new Villain(0, 0);
    }

    /**
     * Checks whether the game is over or not
     * @return A boolean value indicating if the game is over or not.
     */
    public boolean isOver() {
        return hero.getPosition().x == villain.getPosition().x &&
            hero.getPosition().y == villain.getPosition().y;
    }
}
