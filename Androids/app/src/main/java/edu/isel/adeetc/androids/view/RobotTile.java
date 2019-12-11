package edu.isel.adeetc.androids.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import edu.isel.adeetc.androids.R;
import edu.isel.adeetc.poo.Img;
import edu.isel.adeetc.poo.Tile;

public class RobotTile implements Tile {

    private final Img image;
    private final Paint brush;

    public RobotTile(Context context) {
        image = new Img(context, R.drawable.robot);
        brush = new Paint();
    }

    @Override
    public void draw(Canvas canvas, int side) {
        image.draw(canvas, side, side, brush);
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
