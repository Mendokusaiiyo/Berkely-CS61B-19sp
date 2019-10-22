import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    public class Node {
        private Hashtable<Character, Node> link;
        private boolean isKey;

        public Node(Hashtable<Character, Node> link, boolean isKey) {
            this.link = link;
            this.isKey = isKey;
        }
    }

    public MyTrieSet() {

    }

    @Override
    public void clear() {
        root.link.clear();
        root.isKey = false;
    }

    @Override
    public boolean contains(String key) {
        Node currNode = root;
        if (root == null) {
            return false;
        }
        for (int i = 0; i < key.length(); i += 1) {
            if (!currNode.link.containsKey(key.charAt(i))) {
                return false;
            }
            currNode = currNode.link.get(key.charAt(i));
        }
        return currNode.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        if (root == null) {
            root = new Node(new Hashtable<>(), false);
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.link.containsKey(c)) {
                curr.link.put(c, new Node(new Hashtable<>(), false));
            }
            curr = curr.link.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (!root.link.containsKey(prefix.charAt(0))) {
            throw new NoSuchElementException("prefix "+ prefix + " does not exist.");
        }
        Node curr = root;
        List<String> aList = new LinkedList<>();
        for (int i = 0; i < prefix.length(); i += 1) {
            curr = curr.link.get(prefix.charAt(i));
        }
        for (char c : curr.link.keySet()) {
            keysWithPrefix(prefix + c, aList, curr.link.get(c));
        }

        return aList;
    }

    /** A helper method of keysWithPrefix(). */
    private void keysWithPrefix(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.link.keySet()) {
            keysWithPrefix(s + c, x, n.link.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
        for (int i = 0; i < 10; i++) {
            t.add("hi" + i);
        }
        System.out.println(t.contains("zebra"));
        List<String> l = t.keysWithPrefix("he");
        for (String s : l) {
            System.out.println(s);
        }
    }
}
