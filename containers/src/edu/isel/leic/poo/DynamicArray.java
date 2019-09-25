package edu.isel.leic.poo;

public class DynamicArray {

    private static final int INITIAL_CAPACITY = 10;
    private String[] data = new String[INITIAL_CAPACITY];
    private int size = 0;

    private void growArray() {
        String[] newArray = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    public void add(String element) {
        if (size == data.length)
            growArray();
        data[size++] = element;
    }

    public int size() {
        return size;
    }

    public String get(int idx) {
        return data[idx];
    }

    public void addFirst(String element) {
        // TODO:
    }
}
