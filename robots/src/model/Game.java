package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the Robots game state
 */
public class Game {

    public final Hero hero;
    public final LinkedList<Villain> villains;
    private final Actor[][] board;

    private void detectCollisions() {

        LinkedList<Villain> deadVillains = new LinkedList<>();
        for (Villain villain : villains) {
            if (board[villain.getPosition().x][villain.getPosition().y] != villain) {
                deadVillains.add(villain);
                deadVillains.add((Villain) board[villain.getPosition().x][villain.getPosition().y]);
                board[villain.getPosition().x][villain.getPosition().y] = null;
            }
        }

        for (Villain deadVillain : deadVillains) {
            villains.remove(deadVillain);
        }
    }

    /**
     * Initiates the game with the given arena dimensions
     * @param width     The arena width
     * @param height    The arena height
     */
    public Game(int width, int height) {
        hero = new Hero(20, 10, width, height);
        villains = new LinkedList<>();
        villains.add(new Villain(0, 0));
        villains.add(new Villain(width - 1, 0));
        villains.add(new Villain(0, height - 1));
        villains.add(new Villain(width - 1, height - 1));

        board = new Actor[width][height];
        board[hero.getPosition().x][hero.getPosition().y] = hero;
        for (Villain villain : villains) {
            board[villain.position.x][villain.position.y] = villain;
        }
    }

    /**
     * Checks whether the game is over or not
     * @return A boolean value indicating if the game is over or not.
     */
    public boolean isOver() {
        for (Villain villain : villains) {
            if (hero.getPosition().x == villain.getPosition().x &&
                    hero.getPosition().y == villain.getPosition().y)
                return true;
        }
        return false;
    }

    /**
     * Displaces the hero the given delta coordinates
     * @param dx the horizontal delta
     * @param dy the vertical delta
     */
    public void moveHeroBy(int dx, int dy) {
        board[hero.getPosition().x][hero.getPosition().y] = null;
        hero.moveBy(dx, dy);
        board[hero.getPosition().x][hero.getPosition().y] = hero;

        for (Villain villain : villains) {
            board[villain.getPosition().x][villain.getPosition().y] = null;
            villain.moveTowards(hero.getPosition());
            board[villain.getPosition().x][villain.getPosition().y] = villain;
        }

        detectCollisions();
    }
}
