package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> head;
    private int size = 0;

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        Node<T> head = new Node<>(elements[0]);
        Node<T> current = head;
        for (int i = 1; i < elements.length; i++) {
            current.setNext(new Node<>(elements[i]));
            current = current.getNext();
        }
        LinkedStack<T> stack = new LinkedStack<>();
        stack.head = head;
        stack.size = elements.length;
        return stack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<>(element);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T item = head.getItem();
        head = head.getNext();
        size --;
        return item;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node<T>  {
        private T item;
        private Node<T> next;

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

}
