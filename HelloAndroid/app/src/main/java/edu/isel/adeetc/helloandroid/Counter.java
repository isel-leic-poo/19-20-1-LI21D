package edu.isel.adeetc.helloandroid;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

public class Counter {

    interface OnValueChangedListener {
        void valueChanged(Counter source);
    }

    private final List<OnValueChangedListener> listeners = new LinkedList<>();
    private int value = 0;

    private void fireOnValueChanged() {
        for (OnValueChangedListener listener : listeners) {
            listener.valueChanged(this);
        }
    }

    public Counter increment() {
        value += 1;
        fireOnValueChanged();
        return this;
    }

    public Counter decrement() {
        value -= 1;
        fireOnValueChanged();
        return this;
    }

    public int getValue() {
        return value;
    }

    public void addListener(OnValueChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OnValueChangedListener listener) {
        listeners.remove(listener);
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
