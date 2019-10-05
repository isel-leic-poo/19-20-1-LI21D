import isel.leic.pg.Console;
import model.Actor;
import model.Game;
import model.Villain;

import java.util.LinkedList;

/**
 * Represents the Robots game view
 */
public class GameView {

    public final Game game;
    private final ActorView heroView;
    private final LinkedList<ActorView> villainViews;

    public GameView(Game game) {
        this.game = game;
        heroView = new ActorView(game.hero, Console.RED, '@');
        villainViews = new LinkedList<>();

        for (Villain villain : game.villains) {
            villainViews.add(new ActorView(villain, Console.WHITE, '+'));
        }
    }

    public void draw() {
        heroView.draw();
        for (ActorView view : villainViews) {
            view.draw();
        }
    }

    public void clear() {
        heroView.clear();
        for (ActorView view : villainViews) {
            view.clear();
        }
    }
}
