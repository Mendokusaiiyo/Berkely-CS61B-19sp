package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private HashMap<T, Integer> itemIndex;
    private ArrayList<Node> nodeList;

    public ArrayHeapMinPQ() {
        nodeList = new ArrayList<>();
        itemIndex = new HashMap<>();
        nodeList.add(null); // Starts at index 1, and let the 0th item be null.
    }

    public class Node {
        T item;
        double pri;

        public Node(T item, double priority) {
            this.item = item;
            pri = priority;
        }
    }

    /** Returns the index of the parent. */
    int parent(int index) {
        return index / 2;
    }

    /** Returns the index of the left child. */
    int leftChild(int index) {
        return index * 2;
    }

    /** Returns the index of the right child. */
    int rightChild(int index) {
        return index * 2 + 1;
    }

    /** Swims the item to the correct position. */
    void swim(int index) {
        if (index == 1) {
            return;
        }
        if (nodeList.get(parent(index)).pri > nodeList.get(index).pri) {
            swap(index, parent(index));
            swim(parent(index));
        }
        return;
    }

    /** Sinks the item to the correct position. */
    void sink(int index) {
        if (leftChild(index) > size()) {
            return;
        }
        else if (leftChild(index) == size()) {
            swap(index, leftChild(index));
        }
        else if (nodeList.get(index).pri > nodeList.get(leftChild(index)).pri ||
                nodeList.get(index).pri > nodeList.get(rightChild(index)).pri) {
            if (nodeList.get(leftChild(index)).pri < nodeList.get(rightChild(index)).pri) {
                swap(index, leftChild(index));
                sink(leftChild(index));
            }
            else {
                swap(index, rightChild(index));
                sink(rightChild(index));
            }
        }
    }

    /** Swaps the positions of two items. */
    void swap(int index1, int index2) {
        if (index1 == 0 || index2 ==0) {
            return;
        }
        Node swapItem1 = nodeList.get(index1);
        Node swapItem2= nodeList.get(index2);
        nodeList.set(index1, swapItem2);
        nodeList.set(index2, swapItem1);
        itemIndex.put(swapItem1.item, index2);
        itemIndex.put(swapItem2.item, index1);
    }


    @Override
    public void add(T item, double priority) {
        Node node = new Node(item, priority);
        if (nodeList.contains(node)) {
            throw new IllegalArgumentException();
        }
        nodeList.add(node);
        itemIndex.put(item, size());
        swim(size());

    }

    @Override
    public boolean contains(T item) {
        return itemIndex.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return nodeList.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T min = nodeList.get(1).item;
        swap(1, size());
        nodeList.remove(size());
        itemIndex.remove(min);
        sink(1);
        return min;
    }

    @Override
    public int size() {
        return (nodeList.size() - 1);
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        double oldPri = nodeList.get(itemIndex.get(item)).pri;
        nodeList.get(itemIndex.get(item)).pri = priority;
        if (priority > oldPri) {
            sink(itemIndex.get(item));
        }
        swim(itemIndex.get(item));
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 1000000; i += 1) {
            pq.add(i, i + 1);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total add() time elapsed: " + (end1 - start1) / 1000.0 + " seconds");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            pq.changePriority(i, i + 2);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total changePriority() time elapsed: " + (end2 - start2) / 1000.0 + " seconds");
    }
}
