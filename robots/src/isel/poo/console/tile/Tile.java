package isel.poo.console.tile;

import isel.poo.console.View;

/**
 * Base class hierarchy of Tiles
 */
public class Tile extends View {

    public void setBackground(int color) { bkColor = color; }
    protected void init() {}
}
