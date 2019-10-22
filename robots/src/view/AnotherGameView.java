package view;

import isel.leic.pg.Console;
import isel.poo.console.tile.Tile;
import isel.poo.console.tile.TilePanel;
import model.Game;
import model.JunkPile;
import model.Villain;

import java.util.LinkedList;
import java.util.List;

public class AnotherGameView implements Game.GameListener {

    private final Game game;
    private final TilePanel board;
    private final ActorTile heroView;
    private final List<ActorTile> hazardsViews;

    @Override
    public void onVillainDeath(Villain villain, JunkPile junkPile) {
        // TODO:
    }

    public AnotherGameView(Game game, int width, int height) {
        this.game = game;
        game.setGameListener(this);

        board = new TilePanel(height, width, 2);

        heroView = new ActorTile(game.hero, Console.RED, '@');
        board.setTile(game.hero.getPosition().y, game.hero.getPosition().x, heroView);

        hazardsViews = new LinkedList<>();

        for (Villain villain : game.villains) {
            final ActorTile tile = new ActorTile(villain, Console.BLUE, '+');
            hazardsViews.add(tile);
            board.setTile(villain.getPosition().y, villain.getPosition().x, tile);
        }

        for (JunkPile junkPile : game.junk) {
            final ActorTile tile = new ActorTile(junkPile, Console.BLACK, '*');
            hazardsViews.add(tile);
            board.setTile(junkPile.getPosition().y, junkPile.getPosition().x, tile);
        }

        board.repaint();
    }


    public boolean moveStuff(int dx, int dy) {

        board.setTile(game.hero.getPosition().y, game.hero.getPosition().x, new Tile());
        for (ActorTile tile : hazardsViews) {
            board.setTile(tile.actor.getPosition().y, tile.actor.getPosition().x, new Tile());
        }

        game.moveHeroBy(dx, dy);

        board.setTile(game.hero.getPosition().y, game.hero.getPosition().x, heroView);
        for (ActorTile tile : hazardsViews) {
            board.setTile(tile.actor.getPosition().y, tile.actor.getPosition().x, tile);
        }

        return game.isOver();
    }
}
