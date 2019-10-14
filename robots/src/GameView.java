import isel.leic.pg.Console;
import model.Game;
import model.JunkPile;
import model.Villain;

import java.util.LinkedList;

/**
 * Represents the Robots game view
 */
public class GameView {

    public final Game game;
    private final ActorView heroView;

    public GameView(Game game) {
        this.game = game;
        heroView = new ActorView(game.hero, Console.RED, '@');
    }

    public void draw() {
        heroView.draw();

        final LinkedList<ActorView> villainViews = new LinkedList<>();
        for (Villain villain : game.villains)
            villainViews.add(new ActorView(villain, Console.WHITE, '+'));

        final LinkedList<ActorView> junkViews = new LinkedList<>();
        for (JunkPile junk : game.junk) {
            junkViews.add(new ActorView(junk, Console.WHITE, '*'));
        }

        for (ActorView view : villainViews)
            view.draw();
        for (ActorView view : junkViews)
            view.draw();
    }

    public void clear() {
        heroView.clear();

        final LinkedList<ActorView> views = new LinkedList<>();
        for (Villain villain : game.villains)
            views.add(new ActorView(villain, Console.WHITE, '+'));

        for (ActorView view : views)
            view.clear();
    }
}
