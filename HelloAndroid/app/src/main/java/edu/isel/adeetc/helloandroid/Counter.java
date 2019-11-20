package edu.isel.adeetc.helloandroid;

import androidx.annotation.NonNull;

public class Counter {

    private int value = 0;

    public Counter increment() {
        value += 1;
        return this;
    }

    public Counter decrement() {
        value -= 1;
        return this;
    }

    public int getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
