package view;

import isel.leic.pg.Console;
import model.Actor;

/**
 * Represents the actors view in the Robots game.
 */
public class ActorView {

    /**
     * The actor instance
     */
    public final Actor actor;

    /**
     * The color to be used to paint the hero
     */
    private final int color;

    /**
     * The character to be used to paint the hero
     */
    private final char face;

    /**
     * Initiates the view with the given parameters
     * @param actor the actor instance
     * @param color the view's color
     * @param face  the view's character
     */
    public ActorView(Actor actor, int color, char face) {
        this.actor = actor;
        this.color = color;
        this.face = face;
    }

    /**
     * Paints the hero at its current position
     */
    public void draw() {
        Console.cursor(actor.getPosition().y, actor.getPosition().x);
        Console.setForeground(color);
        Console.print(face);
    }

    /**
     * Clears the view
     */
    public void clear() {
        Console.cursor(actor.getPosition().y, actor.getPosition().x);
        Console.setForeground(color);
        Console.print(' ');
    }
}
