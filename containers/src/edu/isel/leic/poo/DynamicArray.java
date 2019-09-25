package edu.isel.leic.poo;

/**
 * Class whose instances represent dynamic arrays.
 */
public class DynamicArray {

    /**
     * The initial capacity of the underlying array.
     */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * The array that holds the data.
     */
    private String[] data = new String[INITIAL_CAPACITY];

    /**
     * The number of elements stored in the array.
     */
    private int size = 0;

    /**
     * Helper method used to grow the underlying array whenever its capacity is exhausted
     */
    private void growArray() {
        String[] newArray = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    /**
     * Stores the given element at the end of the array
     * @param element   The element to be stored
     */
    public void add(String element) {
        if (size == data.length)
            growArray();
        data[size++] = element;
    }

    /**
     * Gets the number of elements in the array.
     * @return The number of elements stored in the array.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the element at the specified position
     * @param idx   The element´s position, in the interval [0..size[
     * @return  The corresponding element
     */
    public String get(int idx) {
        // TODO: Check pre-conditions
        return data[idx];
    }

    public void addFirst(String element) {
        // TODO:
    }
}
