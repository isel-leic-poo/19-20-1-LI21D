package edu.isel.adeetc.helloandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CounterControl extends View {

    private final Paint brush;
    private Counter model;

    public CounterControl(Context context) {
        super(context);
        Log.v(MainActivity.TAG, "CounterControl()");
        brush = new Paint();
        brush.setColor(Color.RED);
        brush.setStrokeWidth(8);
        brush.setStyle(Paint.Style.STROKE);

        setOnTouchListener(new OnTouchListener() {
            private MotionEvent.PointerCoords start = new MotionEvent.PointerCoords();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    event.getPointerCoords(0, start);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    MotionEvent.PointerCoords end = new MotionEvent.PointerCoords();
                    event.getPointerCoords(0, end);
                    if (end.y - start.y < 0) model.increment();
                    else model.decrement();
                }
                Log.v(MainActivity.TAG, "onTouch() " + event.getAction());
                return true;
            }
        });
    }

    public void setModel(Counter counter) {
        model = counter;
        if (model != null) {
            model.addListener(source -> invalidate());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v(MainActivity.TAG, "onDraw(): width = " + getWidth() + " ; height = " + getHeight());
        int value = model == null ? 0 : model.getValue();
        int height = getHeight() / 2 - value * 100;
        canvas.drawLine(0, height, getWidth(), height, brush);
    }
}
