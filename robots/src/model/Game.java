package model;

import java.util.LinkedList;

/**
 * Represents the Robots game state
 */
public class Game {

    /**
     * Contract to be supported by listeners of game events
     */
    public interface GameListener {
        
        /**
         * Signals that a villain has died
         * @param villain   the corpse
         * @param junkPile  the junk pile that replaced the villain in the board
         */
        void onVillainDeath(Villain villain, JunkPile junkPile);
    }

    public final Hero hero;
    public final LinkedList<Villain> villains;
    public final LinkedList<JunkPile> junk;
    private final Actor[][] board;

    private GameListener listener;

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
            else {
                for (JunkPile junkPile: junk) {
                    if (villain.getPosition().equals(junkPile.getPosition())) {
                        deadVillains.add(villain);
                        board[x][y] = junkPile;
                    }
                }
            }
        }

        for (Villain deadVillain : deadVillains) {
            villains.remove(deadVillain);
            final JunkPile pile = (JunkPile) board[deadVillain.getPosition().x][deadVillain.getPosition().y];
            if (listener != null)
                listener.onVillainDeath(deadVillain, pile);
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
        junk.add(new JunkPile(1, 1));

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

        if (villains.isEmpty())
            return true;

        for (Villain villain : villains) {
            if (hero.getPosition().equals(villain.getPosition()))
                return true;
        }

        for (JunkPile junkPile : junk) {
            if (hero.getPosition().equals(junkPile.getPosition()))
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

    /**
     * Register the given listener to be notified when relevant game events occur
     * @param listener  the listener instance
     */
    public void setGameListener(GameListener listener) {
        this.listener = listener;
    }
}
