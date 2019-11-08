package view;

import isel.leic.pg.Console;

import model.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the Robots game view. The view implementation does not use the
 * Observer pattern.
 */
public class GameView {

    private final Game game;
    private final ActorView heroView;
    private List<ActorView> hazardsViews;

    /**
     * Builds the views that are used to display the game's hazards (i.e. villains and junk piles)
     */
    private void buildHazardViews() {
        hazardsViews = new LinkedList<>();

        for (Actor actor : game) {
            hazardsViews.add(
                new ActorView(
                    actor,
                    Console.WHITE,
                    actor instanceof Villain ? '+' : '*'
                )
            );
        }
    }

    /**
     * Initiates an instance with the given game.
     * @param game  the model instance
     */
    public GameView(Game game) {
        this.game = game;
        heroView = new ActorView(game.hero, Console.RED, '@');

        buildHazardViews();
        draw();
    }

    /**
     * Moves the heroby the given deltas.
     * @param dx    the horizontal delta
     * @param dy    the vertical delta
     */
    public void moveHero(int dx, int dy) {
        clear();
        game.moveHeroBy(dx, dy);
        buildHazardViews();
        draw();
    }

    /**
     * Draws the views
     */
    private void draw() {
        heroView.draw();
        for (ActorView view : hazardsViews)
            view.draw();
    }

    /**
     * Clears the views
     */
    private void clear() {
        heroView.clear();
        for (ActorView view : hazardsViews)
            view.clear();
    }
}
