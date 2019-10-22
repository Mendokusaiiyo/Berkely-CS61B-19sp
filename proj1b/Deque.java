public interface Deque<T> {
    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T x);


    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T x);

    /**
     * Returns true if deque is empty, false otherwise.
     */
    default public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size();


    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque();


    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst();

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null
     */
    public T removeLast();


    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index);


    /**
     * Computes the index before the given index.
     */
    public int minusOne(int index);

    /**
     * Computes the index after the given index.
     */
    public int plusOne(int index);

    /**
     * Returns true if the deque is full, false otherwise.
     */
    public boolean isFull();

    /**
     * Decides whether to downsize the deque and returns true if necessary.
     */
    public boolean isSparse();

    /**
     * Resizes the deque.
     */
    public void resize(int capacity);

    /**
     * Upsizes the deque.
     */
    public void upsize();

    /**
     * Downsizes the deque.
     */
    public void downsize();

}

