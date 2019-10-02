import isel.leic.pg.Console;
import model.Game;

/**
 * Represents the Robots game view
 */
public class GameView {

    public final Game game;
    private final ActorView heroView;
    private final ActorView villainView;

    public GameView(Game game) {
        this.game = game;
        heroView = new ActorView(game.hero, Console.RED, '@');
        villainView = new ActorView(game.villain, Console.WHITE, '+');
    }

    public void draw() {
        heroView.draw();
        villainView.draw();
    }

    public void clear() {
        heroView.clear();
        villainView.clear();
    }
}
