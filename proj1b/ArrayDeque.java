public class ArrayDeque<T> implements Deque<T> {
	private T[] item;
	private int size;
	private int nextFirst;
	private int nextLast;

	/** Creates an empty list. */
	public ArrayDeque() {
	item = (T[])new Object[8];
	nextFirst = 0;
	nextLast = 1;
	}

    /** Computes the index before the given index. */
    @Override
    public int minusOne(int index) {
        return (index - 1 + item.length) % item.length;
    }

    /** Computes the index after the given index. */
    @Override
    public int plusOne(int index) {
        return (index+1) % item.length;
    }

	/** Adds an item of type T to the front of the deque. */
    @Override
	public void addFirst(T x) {
	    if (isFull()) {
	        upsize();
        }
		item[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
		size += 1;
	}

	/** Adds an item of type T to the back of the deque. */
    @Override
	public void addLast(T x) {
	    if (isFull()) {
	        upsize();
        }
		item[nextLast] = x;
		nextLast = plusOne(nextLast);
		size += 1;
	}

	/** Returns true if deque is empty, false otherwise. */
    @Override
	public boolean isEmpty() {
		return size == 0;
	}

	/** Returns true if the deque is full, false otherwise. */
    @Override
	public boolean isFull() {
	    return size == item.length;
    }

    /** Decides whether to downsize the deque and returns true if necessary. */
    @Override
    public boolean isSparse() {
        return size < (item.length / 2) && item.length > 16;
    }

	/** Returns the number of items in the deque. */
    @Override
	public int size() {
		return size;
	}

	/** Prints the items in the deque from first to last, separated by a space. 
	Once all the items have been printed, print out a new line. */
    @Override
	public void printDeque() {
        for (int indexZero = nextFirst + 1; indexZero < item.length; indexZero += 1) {
            System.out.print(item[indexZero] + " ");
        }
        for (int i = 0; i < nextLast; i += 1) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
	}

	/** Removes and returns the item at the front of the deque. 
	If no such item exists, returns null. */
    @Override
	public T removeFirst() {
	    if (isEmpty()) {
	        return null;
        }
	    if (isSparse()) {
	        downsize();;
        }
		nextFirst = plusOne(nextFirst);
		T remove = item[nextFirst];
		item[nextFirst] = null;
		size -= 1;
		return remove;
	}

    /** Removes and returns the item at the back of the deque.
     If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return  null;
        }
        if (isSparse()) {
            downsize();
        }
        nextLast = minusOne(nextLast);
        T remove = item[nextLast];
        item[nextLast] = null;
        size -= 1;
        return remove;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return item[plusOne(nextFirst + index)];
    }

    /** Resizes the deque. */
    @Override
    public void resize(int capacity) {
    	T[] newitem = (T[])new Object[capacity];
    	int oldnextFirst = nextFirst;
    	nextFirst = nextFirst + item.length;
    	System.arraycopy(item, plusOne(oldnextFirst), newitem,
                (nextFirst + 1) % capacity, item.length - plusOne(oldnextFirst));
    	System.arraycopy(item, 0, newitem, 0, nextLast);
    	item = newitem;
    }

    /** Upsizes the deque. */
    @Override
    public void upsize() {
        resize(size * 2);
    }

    /** Downsizes the deque. */
    @Override
    public void downsize() {
        resize((item.length) / 2);
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque<T> other) {
        item = (T[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        System.arraycopy(other.item, 0, item, 0, other.size);
    }

	public static void main(String[] args) {
		ArrayDeque<Integer> L = new ArrayDeque();
        L.addFirst(2);
        L.addFirst(1);
        L.addLast(3);
        L.addLast(4);
        L.removeLast();
        L.removeFirst();
        System.out.println(L.get(2));
		L.printDeque();
	}
}