package edu.isel.adeetc.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;

public class DrawView extends View {

    private final Paint brush;
    private Drawing drawing;

    public DrawView(Context context) {
        super(context);
        brush = new Paint();
        brush.setColor(Color.RED);
        brush.setStrokeWidth(8);
        brush.setStyle(Paint.Style.STROKE);

        setOnTouchListener(new OnTouchListener() {
            private Figure currentFigure = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final MotionEvent.PointerCoords coordinates = new MotionEvent.PointerCoords();
                event.getPointerCoords(0, coordinates);

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    currentFigure = new Figure();
                    drawing.addFigure(currentFigure);
                }

                currentFigure.addPoint(new Point((int) coordinates.x, (int) coordinates.y));

                if(event.getAction() == MotionEvent.ACTION_UP)
                    currentFigure = null;

                invalidate();
                return true;
            }
        });
    }

    public void setModel(Drawing drawing) {
        this.drawing = drawing;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Figure figure : drawing) {
            drawFigure(figure, canvas);
        }
    }

    private void drawFigure(Figure figure, Canvas canvas) {

        if (figure.isBlank())
            return;

        Iterator<Point> pointsIterator = figure.iterator();
        Point lineStart = pointsIterator.next();

        while (pointsIterator.hasNext()) {
            final Point current = pointsIterator.next();
            canvas.drawLine(lineStart.x, lineStart.y, current.x, current.y, brush);
            lineStart = current;
        }
    }
}
