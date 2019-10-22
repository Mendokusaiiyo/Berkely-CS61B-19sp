public class LinkedListDeque<T> {
	public class stuffNode {
		private stuffNode prev;
		private T item;
		private stuffNode next;

		private stuffNode(stuffNode p, T i, stuffNode n) {
			prev = p;
			item = i;
			next = n;
		}
	}
	private stuffNode sentinel;
	private int size;
	/** Creates an empty linked list Deque. */
	public LinkedListDeque() {
		sentinel = new stuffNode(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		size = 0;
	}

	/** Adds an item of type T to the front of the deque. */
	public void addFirst(T x) {
		sentinel.next = new stuffNode(sentinel, x, sentinel.next);
		sentinel.next.next.prev = sentinel.next;
		size += 1;
	}

	/** Adds an item of type T to the back of the deque. */
	public void addLast(T x) {
		sentinel.prev = new stuffNode(sentinel.prev, x, sentinel);
		sentinel.prev.prev.next = sentinel.prev;
		size += 1;
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Returns the number of items in the deque. */
	public int size() {
		return size;
	}

	/** Prints the items in the deque from first to last, separated by a space. 
	Once all the items have been printed, print out a new line. */
	public void printDeque() {
		while (sentinel.next.item != null) {
			System.out.print(sentinel.next.item + " ");
			sentinel = sentinel.next;
		}
		System.out.println();
	}

	/** Removes and returns the item at the front of the deque. 
	If no such item exists, returns null. */
	public T removeFirst() {
		stuffNode list = sentinel.next;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size -= 1;
		return list.item;
	}

	/** Removes and returns the item at the back of the deque.
	If no such item exists, returns null */
	public T removeLast() {
		stuffNode list2 = sentinel.prev;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size -= 1;
		return list2.item;
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
	If no such item exists, returns null. Must not alter the deque! */
	public T get(int index) {
		stuffNode list3 = sentinel.next;
		int num = 0;
		while (num < index) {
			list3 = list3.next;
			num += 1;
		}
		return list3.item;
	}

	/** Same as get, but uses recursion.
	First, need a helper method. */
	public T getRecursive(int index, stuffNode list3) {
		if (index == 0) {
			return list3.item;
		}
		return getRecursive(index - 1, list3.next);
	}
	public T getRecursive(int index) {
		return getRecursive(index, sentinel.next);
	}

	/** Creates a deep copy of other. */
	public LinkedListDeque(LinkedListDeque<T> other) {
		sentinel = new stuffNode(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		size = 0;
		for (int i = 0; i < other.size(); i += 1) {
			addLast(other.get(i));
		}
	}

	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque();
		L.addFirst(5);
		L.addFirst(4);
		L.addFirst(3);
		L.addLast(6);
		L.addLast(7);
		System.out.println(L.getRecursive(4));
		L.removeFirst();
		L.removeLast();
		L.printDeque();
	}
}