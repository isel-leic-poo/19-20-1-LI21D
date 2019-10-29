package view;

import isel.leic.pg.Console;
import isel.poo.console.tile.Tile;
import isel.poo.console.tile.TilePanel;
import model.*;

/**
 * Represents the Robots game view, whose implementation is based on a TilePanel.
 * The implementation makes use of the Observer pattern.
 */
public class TiledGameView implements Game.GameListener {

    /**
     * The tile panel used to contain the individual views
     */
    private final TilePanel board;

    @Override
    public void onVillainDeath(Villain villain, JunkPile junkPile) {
        board.setTile(
                villain.getPosition().y,
                villain.getPosition().x,
                new ActorTile(junkPile, Console.BLACK, '*'));
    }

    @Override
    public void onActorMoved(Actor actor, Coordinate from) {
        final Tile view = board.getTile(from.y, from.x);
        board.setTile(from.y, from.x, new Tile());
        board.setTile(actor.getPosition().y, actor.getPosition().x, view);
    }

    /**
     * Initiates view instance for the given game with the specified dimensions.
     * @param game      The game instance
     * @param width     The board width
     * @param height    The board height
     * @param tileSide  The side of each tile
     */
    public TiledGameView(Game game, int width, int height, int tileSide) {

        game.addGameListener(this);

        board = new TilePanel(height, width, tileSide);

        final ActorTile heroView = new ActorTile(game.hero, Console.RED, '@');
        board.setTile(game.hero.getPosition().y, game.hero.getPosition().x, heroView);


        for (Villain villain : game.villains) {
            final ActorTile tile = new ActorTile(villain, Console.BLUE, '+');
            board.setTile(villain.getPosition().y, villain.getPosition().x, tile);
        }

        for (JunkPile junkPile : game.junk) {
            final ActorTile tile = new ActorTile(junkPile, Console.BLACK, '*');
            board.setTile(junkPile.getPosition().y, junkPile.getPosition().x, tile);
        }

        board.repaint();
    }
}
