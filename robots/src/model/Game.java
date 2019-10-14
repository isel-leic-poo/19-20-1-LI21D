package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the Robots game state
 */
public class Game {

    public final Hero hero;
    public final LinkedList<Villain> villains;
    public final LinkedList<JunkPile> junk;
    private final Actor[][] board;

    private void detectCollisions() {

        LinkedList<Villain> deadVillains = new LinkedList<>();
        for (Villain villain : villains) {
            final int x = villain.getPosition().x;
            final int y = villain.getPosition().y;
            if (board[x][y] != null && board[x][y] != villain) {
                deadVillains.add(villain);
                if (!(board[x][y] instanceof JunkPile)) {
                    deadVillains.add((Villain) board[x][y]);
                    final JunkPile pile = new JunkPile(x, y);
                    board[x][y] = pile;
                    junk.add(pile);
                }
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
        junk = new LinkedList<>();

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
            final int heroX = hero.getPosition().x;
            final int heroY = hero.getPosition().y;
            if (heroX == villain.getPosition().x && heroY == villain.getPosition().y)
                return true;
        }

        for (JunkPile junkPile : junk) {
            final int heroX = hero.getPosition().x;
            final int heroY = hero.getPosition().y;
            if (heroX == junkPile.getPosition().x && heroY == junkPile.getPosition().y)
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
        final int heroX = hero.getPosition().x;
        final int heroY = hero.getPosition().y;
        board[heroX][heroY] = null;
        hero.moveBy(dx, dy);
        board[heroX][heroY] = hero;

        for (Villain villain : villains) {
            board[villain.getPosition().x][villain.getPosition().y] = null;
            villain.moveTowards(hero.getPosition());
            board[villain.getPosition().x][villain.getPosition().y] = villain;
        }

        detectCollisions();
    }
}
