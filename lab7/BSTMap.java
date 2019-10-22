import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    public class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    /** Initializes an empty BSTMap. */
    public BSTMap() {

    }

    @Override
    public void clear() {
        root = null;
    }

    /** Returns the number of Nodes in the Map. */
    @Override
    public int size() {
        return size(root);
    }

    /** Returns the number of Nodes in the subtree. */
    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }



    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    /** A helper method of get(K ket). */
    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        }
        if (cmp < 0) {
            return  get(x.left, key);
        }
        return x.val;
    }

    @Override
    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        root = put(root, key, val);
    }

    /** A helper method of put. */
    private Node put(Node x, K key, V val) {
        if (x == null) {
        return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Prints out your BSTMap in order of increasing Key. */
    public void printInOrder() {
        for (int i = 0; i < size(); i += 1) {
            System.out.println(select(i).key + ": " + select(i).val);
        }
    }

    /** Returns the key of the Node whose rank is k.
     *  This is the (k + 1)st smallest key in the map.
     */
    private Node select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException(k + "is an invalid argument to select()");
        }
        return select(root, k);
    }

    /** Helper method.
     *  This is the Node whose key ranks k.
     */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        }
        else if (t < k) {
            return select(x.right, k - t - 1);
        }
        return x;
    }

    @Override
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            BSTSet.add(select(i).key);
        }
        return BSTSet;
    }


    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V toRemove = get(key);
        root = remove(root, key);
        return toRemove;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (!get(key).equals(value)) {
            return null;
        }
        return value;
    }

    /** Returns the Node excludes the Node whose Key is key. */
    private Node remove(Node x, K key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        }
        else if (cmp > 0) {
            x.right = remove(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            // If neither left and right nodes are null,
            // then replace this node with the left-most node in the right or
            // right-most node in the left.(Here we use the left-most node in
            // the right.
            Node tmp = x;
            x = min(tmp.right);
            x.right = minDelete(tmp.right);
            x.left = tmp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Returns the Node whose Key is the smallest in the subtree. */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /** Returns the Node that excludes the Node whose Key is the smallest. */
    private Node minDelete(Node x) {
        // If x.left == null means the key of x.left is the smallest.
        if (x.left == null) {
            return x.right;
        }
        x.left = minDelete(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("iterator() is not required so far");
    }

}
