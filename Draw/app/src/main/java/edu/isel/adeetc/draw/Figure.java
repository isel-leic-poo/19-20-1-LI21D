package edu.isel.adeetc.draw;

import android.graphics.Point;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.LinkedList;

public class Figure implements Iterable<Point> {

    private final LinkedList<Point> points = new LinkedList<>();

    @NonNull
    @Override
    public Iterator<Point> iterator() {
        return points.iterator();
    }

    public Figure addPoint(Point point) {
        points.add(point);
        return this;
    }

    public boolean isBlank() {
        return points.size() == 0;
    }
}
