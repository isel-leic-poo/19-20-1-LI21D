package edu.isel.leic.poo;

/**
 * Class whose instances represent linked lists.
 */
public class LinkedList<T> {

    /**
     * Class whose instances represent the linked list individual nodes.
     */
    static class Node<T> {
        public final T element;
        public Node<T> next;

        public Node(T element) {
            this(element, null);
        }

        public Node(T element, Node next) {
            this.next = next;
            this.element = element;
        }
    }

    /**
     * References the first element of the list.
     */
    private Node<T> head = null;

    /**
     * References the last element of the list.
     */
    private Node<T> tail = null;

    /**
     * The number of elements stored in the list.
     */
    private int size = 0;

    /**
     * Stores the given element at the end of the list
     * @param element   The element to be stored
     */
    public void add(T element) {
        if (size == 0) {
            addFirst(element);
            return;
        }

        tail = tail.next = new Node<T>(element);
        size += 1;
    }

    /**
     * Gets the number of elements in the list.
     * @return The number of elements stored in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the element at the specified position
     * @param idx   The element´s position, in the interval [0..size[
     * @return  The corresponding element
     */
    public T get(int idx) {
        // TODO: Check pre-conditions
        Node<T> iterator = head;
        while (idx-- != 0) {
            iterator = iterator.next;
        }
        return iterator.element;
    }

    /**
     * Adds the element to the start of the list
     * @param element the element to be added
     */
    public void addFirst(T element) {
         head = new Node<T>(element, head);
         if (tail == null)
             tail = head;
         size += 1;
    }
}
