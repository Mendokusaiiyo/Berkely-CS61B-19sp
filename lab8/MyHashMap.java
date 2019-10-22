import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    int size;
    double threshold;
    HashSet<K> keySet = new HashSet<>();
    LinkedList<Mapping<K, V>> [] buckets;
    public class Mapping<K, V> {
        int hashCode;
        private K key;
        private V val;
        public Mapping(int hashCode, K key, V val) {
            this.hashCode = hashCode;
            this.key = key;
            this.val = val;
        }
    }

    public MyHashMap() {
        buckets = new LinkedList[INITIAL_CAPACITY];
        threshold = LOAD_FACTOR;
        size = 0;

    }

    public MyHashMap(int initialSize) {
        buckets = new LinkedList[initialSize];
        threshold = LOAD_FACTOR;
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
       buckets = new LinkedList[initialSize];
       threshold = loadFactor;
       size = 0;
    }

    /** Rewrites the hashcode of key. */
    public int hashCode(K key, int M) {
        return ((key.hashCode() & 0x7fffffff) % M);
    }
    @Override
    public void clear() {
        buckets = new LinkedList[buckets.length];
        keySet.clear();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int hash = hashCode(key, buckets.length);
        return get(hash, key).val;
    }

    /** A helper method of get(). */
    private Mapping<K, V> get(int hashcode, K key) {
        for (Mapping mapping : buckets[hashcode]) {
            if (mapping.key.equals(key)) {
                return mapping;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hash = hashCode(key, buckets.length);
        if (key == null) {
            throw new IllegalArgumentException();
        }
        put(hash, key, value);
    }

    public void put(int hashCode, K key, V value) {
        if (buckets[hashCode] == null) {
            buckets[hashCode] = new LinkedList<>();
        }
        Mapping<K, V> mapping = new Mapping<>(hashCode, key, value);
        if (containsKey(key)) {
            get(hashCode, key).val = value;
        } else {
            keySet.add(key);
            buckets[hashCode].add(mapping);
            size += 1;
        }
        double loadFactor = ((double) size) / buckets.length;
        if (loadFactor > threshold) {
            resize();
        }
    }

    public void resize() {
        LinkedList<Mapping<K, V>> [] newBuckets = new LinkedList[buckets.length * 2];
        for (int i = 0; i < buckets.length; i += 1) {
            if (buckets[i] == null) {
                continue;
            }
            for (Mapping<K, V> x : buckets[i]) {
                int newHash = hashCode(x.key, newBuckets.length);
                x.hashCode = newHash;
                if (newBuckets[newHash] == null) {
                    newBuckets[newHash] = new LinkedList<>();
                }
                newBuckets[newHash].add(x);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int hash = hashCode(key, buckets.length);
        Mapping<K, V> removeItem = get(hash, key);
        buckets[hash].remove(removeItem);
        keySet.remove(key);
        size -= 1;
        return removeItem.val;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }

        if(get(key).equals(value)) {
            int hash = hashCode(key, buckets.length);
            Mapping<K, V> removeItem = get(hash, key);
            buckets[hash].remove(removeItem);
            keySet.remove(key);
            size -= 1;
            return removeItem.val;
        }
        return null;

    }

}
