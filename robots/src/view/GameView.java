package view;

import isel.leic.pg.Console;

import model.Game;
import model.JunkPile;
import model.Villain;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the Robots game view
 */
public class GameView implements Game.GameListener {

    private final Game game;
    private final ActorView heroView;
    private final List<ActorView> hazardsViews;

    @Override
    public void onVillainDeath(Villain villain, JunkPile junkPile) {
        ActorView viewToRemove = null;
        for (ActorView view : hazardsViews) {
            if (view.actor == villain) {
                viewToRemove = view;
                break;
            }
        }
        hazardsViews.remove(viewToRemove);
        hazardsViews.add(new ActorView(junkPile, Console.WHITE, '*'));
    }

    public GameView(Game game) {
        this.game = game;
        game.setGameListener(this);
        heroView = new ActorView(game.hero, Console.RED, '@');
        hazardsViews = new LinkedList<>();

        for (Villain villain : game.villains) {
            hazardsViews.add(new ActorView(villain, Console.WHITE, '+'));
        }

        for (JunkPile junkPile : game.junk) {
            hazardsViews.add(new ActorView(junkPile, Console.WHITE, '*'));
        }

        draw();
    }

    public boolean moveStuff(int dx, int dy) {
        clear();
        game.moveHeroBy(dx, dy);
        draw();
        return game.isOver();
    }

    private void draw() {
        heroView.draw();
        for (ActorView view : hazardsViews)
            view.draw();
    }

    private void clear() {
        heroView.clear();
        for (ActorView view : hazardsViews)
            view.clear();
    }
}
