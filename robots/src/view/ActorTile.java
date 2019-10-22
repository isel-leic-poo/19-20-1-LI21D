package view;

import isel.leic.pg.Console;
import isel.poo.console.tile.Tile;
import model.Actor;

public class ActorTile extends Tile {

    /**
     * The actor instance
     */
    public final Actor actor;

    /**
     * The color to be used to paint the actor
     */
    private final int color;

    /**
     * The character to be used to paint the actor
     */
    private final char face;

    /**
     * Initiates the view with the given parameters
     * @param actor         the actor instance
     * @param color         the view's foreground color
     * @param face          the view's character
     */
    public ActorTile(Actor actor, int color, char face) {
        this.actor = actor;
        this.color = color;
        this.face = face;
    }

    @Override
    public void paint() {
        Console.setForeground(color);
        print(0, 0, face);
        print(0, 1, face);
        print(1, 0, face);
        print(1, 1, face);
    }
}
