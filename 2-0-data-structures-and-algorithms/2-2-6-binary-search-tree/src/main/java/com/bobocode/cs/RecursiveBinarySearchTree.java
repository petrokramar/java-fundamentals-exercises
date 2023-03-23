package com.bobocode.cs;

import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        for (T element : elements) {
            tree.insert(element);
        }
        return tree;
    }

    @Override
    public boolean insert(T element) {
        boolean isNew = true;
        if (root == null) {
            root = new Node<>(element);
            size++;
        } else {
            Node<T> current = root;
            while (true) {
                if (element.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = new Node<>(element);
                        size++;
                        break;
                    } else {
                        current = current.left;
                    }
                } else if (element.compareTo(current.element) > 0) {
                    if (current.right == null) {
                        current.right = new Node<>(element);
                        size++;
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    isNew = false;
                    break;
                }
            }
        }
        return isNew;
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        return (element.compareTo(node.element) == 0) ||
                (node.left != null && contains(node.left, element)) ||
                (node.right != null && contains(node.right, element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (root == null) {
            return 0;
        }
        return getDepth(root) - 1;
    }

    private int getDepth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        consumeNode(root, consumer);
    }

    private void consumeNode(Node<T> root, Consumer<T> consumer) {
        if (root.left != null) {
            consumeNode(root.left, consumer);
        }
        consumer.accept(root.element);
        if (root.right != null) {
            consumeNode(root.right, consumer);
        }
    }

    private static class Node<T> {
        private T element;
        private Node<T> left;
        private Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }
}
