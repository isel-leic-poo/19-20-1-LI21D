package model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents the Robots game state
 */
public class Game implements Iterable<Actor> {

    @Override
    public Iterator<Actor> iterator() {
        return new Iterator<>() {
            private Iterator<Villain> villainIterator = villains.iterator();
            private Iterator<JunkPile> junkPileIterator = junk.iterator();

            @Override
            public boolean hasNext() {
                return villainIterator.hasNext() || junkPileIterator.hasNext();
            }

            @Override
            public Actor next() {
                if (villainIterator.hasNext())
                    return villainIterator.next();

                return junkPileIterator.next();
            }
        };
    }

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

        /**
         * Signals that the actor has moved
         * @param actor the actor
         * @param from  the actor's original position
         */
        void onActorMoved(Actor actor, Coordinate from);
    }

    public final Hero hero;

    private final LinkedList<Villain> villains;
    private final LinkedList<JunkPile> junk;
    private final Actor[][] board;
    private LinkedList<GameListener> listeners;

    /**
     * Method used to detect collisions between actors. This method is called each time the player makes a move.
     */
    private void detectCollisions() {

        // TODO: Fix this :s

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
            notifyVillainDeath(deadVillain, pile);
        }
    }

    /**
     * Initiates the game with the given arena dimensions
     * @param width     The arena width
     * @param height    The arena height
     */
    public Game(int width, int height) {
        listeners = new LinkedList<>();

        hero = new Hero(width / 2, height / 2, width, height);
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

        // Move hero
        final Coordinate start = hero.getPosition();
        final int heroX = start.x;
        final int heroY = start.y;
        board[heroX][heroY] = null;
        hero.moveBy(dx, dy);
        board[heroX][heroY] = hero;

        notifyActorMoved(hero, start);

        // Move villains
        for (Villain villain : villains) {
            final Coordinate startPos = villain.getPosition();
            board[villain.getPosition().x][villain.getPosition().y] = null;
            villain.moveTowards(hero.getPosition());
            board[villain.getPosition().x][villain.getPosition().y] = villain;

            notifyActorMoved(villain, startPos);
        }

        // Check what has happened
        detectCollisions();
    }

    /**
     * Notifies all listeners that the actor has moved
     * @param actor the actor that has moved
     * @param start the actor's original position
     */
    private void notifyActorMoved(Actor actor, Coordinate start) {
        for (GameListener listener : listeners) {
            listener.onActorMoved(actor, start);
        }
    }

    /**
     * Notifies all listeners that a villain has died
     * @param deadVillain   the villain that died
     * @param pile          the resulting junk pile
     */
    private void notifyVillainDeath(Villain deadVillain, JunkPile pile) {
        for (GameListener listener : listeners) {
            listener.onVillainDeath(deadVillain, pile);
        }
    }

    /**
     * Register the given listener to be notified when relevant game events occur
     * @param listener  the listener instance
     */
    public void addGameListener(GameListener listener) {
        listeners.add(listener);
    }

    /**
     * Gets the width of the arena, specified upon construction
     * @return  the arena width
     */
    public int getWidth() {
        return board.length;
    }

    /**
     * Gets the height of the arena, specified upon construction
     * @return  the arena height
     */
    public int getHeight() {
        // Implementation note: all the board's arrays have the same size
        return board[0].length;
    }
}
