package com.structures;

/**
 * Simple implementation of a Binary Tree
 *
 * @param <T1> Comparable parameter
 * @param <T2>
 */
public class BinaryTree<T1 extends Comparable<T1>, T2> {
    private Node<T1, T2> root = null;

    public T2 get(T1 key) {
        Node<T1, T2> current = root;
        final int cmp = key.compareTo(current.key);
        while (current != null) {
            if (cmp == 0) {
                return current.value;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    public void add(T1 key, T2 value) {
        final Node<T1, T2> newNode = new Node<>(key, value);
        Node<T1, T2> current = root, currentParent = null;
        while (current != null) {
            final int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                current.value = value;
                return;
            } else {
                currentParent = current;
                current = cmp < 0 ? current.left : current.right;
            }
        }
        if (currentParent == null) {
            root = newNode;
        } else {
            if (key.compareTo(currentParent.key) < 0) {
                currentParent.left = newNode;
            } else {
                currentParent.right = newNode;
            }
        }
    }

    public void remove(T1 key) {
        Node<T1, T2> current = root, currentParent = null;
        while (current != null) {
            final int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                break;
            } else {
                currentParent = current;
                current = cmp < 0 ? current.left : current.right;
            }
        }
        if (current == null) {
            return;
        }
        if (current.right == null) {
            if (currentParent == null) {
                root = current.left;
            } else {
                if (current == currentParent.left) {
                    currentParent.left = current.left;
                } else {
                    currentParent.right = current.left;
                }
            }
        } else {
            Node<T1, T2> rightSubtreeMin = current.right;
            currentParent = null;
            while (rightSubtreeMin.left != null) {
                currentParent = rightSubtreeMin;
                rightSubtreeMin = rightSubtreeMin.left;
            }
            if (currentParent != null) {
                currentParent.left = rightSubtreeMin.right;
            } else {
                current.right = rightSubtreeMin.right;
            }
            current.key = rightSubtreeMin.key;
            current.value = rightSubtreeMin.value;
        }
    }

    /**
     * Node of a Binary Tree
     *
     * @param <T1>
     * @param <T2>
     */
    private static class Node<T1, T2> {
        T1 key;
        T2 value;
        Node<T1, T2> left, right;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }
}
