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
}
