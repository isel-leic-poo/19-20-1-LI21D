package edu.isel.adeetc.draw;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;

public class Drawing implements Iterable<Figure> {

    private final LinkedList<Figure> figures = new LinkedList<>();

    @NonNull
    @Override
    public Iterator<Figure> iterator() {
        return figures.iterator();
    }

    public Drawing addFigure(Figure figure) {
        figures.add(figure);
        return this;
    }

    public String toJSON() {
        final StringBuilder json = new StringBuilder("[ ");
        final Iterator<Figure> itr = figures.iterator();
        while (itr.hasNext()) {
            final Figure figure = itr.next();
            json.append(figure.toJSON());
            if (itr.hasNext())
                json.append(", ");
        }

        json.append(" ]");
        return json.toString();
    }
}