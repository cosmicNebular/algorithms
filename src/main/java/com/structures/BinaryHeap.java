package com.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a Binary Heap
 */
public final class BinaryHeap {
    private List<Integer> list;

    public BinaryHeap() {
        this.list = new ArrayList<>();
    }

    public void add(int value) {
        list.add(value);
        int i = size() - 1;
        int parent = (i - 1) / 2;
        while (i > 0 && list.get(parent) < list.get(i)) {
            int tmp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, tmp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    private void heapify(int i) {
        int leftChild;
        int rightChild;
        int largestChild;

        while (true) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < size() && list.get(leftChild) > list.get(largestChild)) {
                largestChild = leftChild;
            }

            if (rightChild < size() && list.get(rightChild) > list.get(largestChild)) {
                largestChild = rightChild;
            }

            if (largestChild == i) {
                break;
            }

            final int tmp = list.get(i);
            list.set(i, list.get(largestChild));
            list.set(largestChild, tmp);
            i = largestChild;
        }
    }

    public void build(List<Integer> source) {
        list = source;
        for (int i = size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public int getMax() {
        int result = list.get(0);
        list.set(0, list.get(size() - 1));
        list.remove(size() - 1);
        heapify(0);
        return result;
    }

    public int size() {
        return list.size();
    }
}
