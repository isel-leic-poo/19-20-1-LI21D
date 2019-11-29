package edu.isel.adeetc.draw;

import android.graphics.Point;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.LinkedList;

public class Figure implements Iterable<Point> {

    private final LinkedList<Point> points = new LinkedList<>();

    private String toJson(Point p) {
        return "{ " + p.x + ", " + p.y + " }";
    }

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

    public String toJSON() {
        final StringBuilder json = new StringBuilder("[ ");
        final Iterator<Point> itr = points.iterator();
        while (itr.hasNext()) {
            final Point p = itr.next();
            json.append(toJson(p));
            if (itr.hasNext())
                json.append(" ,");
        }

        json.append(" ]");
        return json.toString();
    }
}
