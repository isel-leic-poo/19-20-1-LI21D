package isel.poo.console.tile;

import isel.leic.pg.Console;
import isel.leic.pg.Location;
import isel.poo.console.ParentView;

public class TilePanel extends ParentView {
    private final Tile[][] tiles;
    private final int side;

    public TilePanel(int tilesHeight, int tilesWidth, int tileSide) {
        super(0,0, Console.BLACK);
        tiles = new Tile[tilesHeight][tilesWidth];
        side = tileSide;
        height = tilesHeight * side;
        width = tilesWidth * side;
        for (int l = 0; l < tilesHeight; l++)
            for (int c = 0; c < tilesWidth; c++)
                addTile(l,c,new Tile());
    }

    public Location getModelPosition(int line, int col) {
        assert(parent==null);
        int l = (line-top) / side;
        int c = (col-left) / side;
        return  (l<0 || c<0 || l>=tiles.length || c>=tiles[0].length) ? null : new Location(l , c);
    }

    public void setTile(int l, int c, Tile tile) {
        Tile old = tiles[l][c];
        if (old!=null) children.remove(old);
        addTile(l, c, tile);
        if (tile!=null) tile.repaint();
        else printBox(l*side,c*side,side,side,bkColor);
    }

    private void addTile(int l, int c, Tile t) {
        tiles[l][c] = t;
        if (t==null) return;
        addView(t);
        t.setOrig(l * side, c * side);
        t.setSize(side,side);
        t.setBackground(l%2==0 && c%2!=0 || l%2!=0 && c%2==0 ? Console.GRAY:Console.LIGHT_GRAY);
        t.init();
    }

    public Tile getTile(int l, int c) {
        return tiles[l][c];
    }
}
