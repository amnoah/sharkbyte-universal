package sharkbyte.universal.data;

import java.io.Serializable;
import java.util.*;

/*
 * This class is largely untested and likely holds major faults. Until audited by another developer, I'd use with caution.
 */

/**
 * A basic ArraySet implementation. Compared to a HashSet, an ArraySet has the benefit of maintaining the order in which
 * objects are added to the Set and is more memory efficient due to the use of a single array compared to a HashMap.
 * This does not mean an ArraySet is necessarily better, however, as a HashMap is more efficient in terms of performance
 * when adding to, removing from, and searching the Set.
 * <p>
 * It's worthy to note that when duplicate objects are entered the original position will be maintained.
 *
 * @Authors: am noah
 * @Since: 1.0.0
 * @Updated: 1.0.0
 */
public class ArraySet<E> extends AbstractSet<E> implements Set<E>, Serializable {

    // Increment with any changes.
    private static final long serialVersionUID = 1L;

    protected Object[] data;
    protected int size = 0;

    /*
     * Initialize the object.
     */

    /**
     * Initialize the ArraySet object with no inputs.
     */
    public ArraySet() {
        data = new Object[15];
    }

    /**
     * Initialize the ArraySet object with an inputted array starting size.
     */
    public ArraySet(int initialSize) {
        if (initialSize < 0) throw new IllegalArgumentException("Initial size must be >= 0.");
        data = new Object[initialSize];
    }

    public ArraySet(ArraySet<E> other) {
        data = Arrays.copyOf(other.data, other.data.length, Object[].class);
        size = other.size;
    }

    @SafeVarargs
    public ArraySet(E... objects) {
        data = new Object[objects.length];
        addAll(Arrays.asList(objects));
    }

    /**
     * Initialize the ArraySet object with an inputted Collection,
     */
    public ArraySet(Collection<? extends E> collection) {
        // Cannot contain duplicate objects if it's a Set.
        if (collection instanceof Set) {
            Object[] arr = collection.toArray();
            if ((size = arr.length) > 0) data = Arrays.copyOf(arr, size, Object[].class);
            else data = new Object[0];
        } else {
            data = new Object[15];
            addAll(collection);
        }
    }

    /*
     * Functions from only this class.
     */

    /**
     * Adds the inputted object into the given index of the data array.
     * Returns false if the object was moved from another index to the inputted index.
     */
    public boolean add(int index, E object) {
        rangeCheckAllowSize(index);
        boolean hadElement = remove(object);
        checkGrowArray();
        // Shift everything past the index up a spot.
        if (index != size) for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = object;
        size++;
        return !hadElement;
    }



    /**
     * Grows the data array by 10 places if required.
     */
    protected void checkGrowArray() {
        if (size == data.length) data = Arrays.copyOf(data, data.length + 10);
    }

    /**
     * Shrinks the data array by 10 places.
     */
    protected void checkShrinkArray() {
        if ((data.length - size) >= 15) data = Arrays.copyOf(data, data.length - 10);
    }

    /**
     * Returns the object at the given index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    public int indexOf(E object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (!(data[i] == null)) continue;
                return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!object.equals(data[i])) continue;
                return i;
            }
        }

        return -1;
    }

    /**
     * Checks if the given index is within the usable section of the data array, throwing an exception if not.
     */
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid array index.");
    }

    /**
     * Checks if the given index is within the usable section of the data array or within +1, throwing an exception if not.
     */
    protected void rangeCheckAllowSize(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Invalid array index.");
    }

    /**
     * Removes the element at the specified index, shifting all values over.
     */
    public void remove(int index) {
        rangeCheck(index);
        // Shift all elements past the removed object down a spot.
        for (int i = index; i < size; i++) data[i] = data[i + 1];
        size--;
        checkShrinkArray();
    }

    /*
     * Functions inherited from the Collection class.
     */

    /**
     * Adds the inputted object to the Set.
     * Returns false if the element was already in the Set.
     */
    @Override
    public boolean add(E a) {
        if (contains(a)) return false;
        checkGrowArray();
        data[size] = a;
        size++;
        return true;
    }

    /**
     * Returns an iterator for this Set.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArraySetIterator();
    }

    /**
     * Removes the inputted object from the Set.
     * Returns True if the Set had the object, returns False if it did not.
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (!(data[i] == null)) continue;
                remove(i);
                return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!o.equals(data[i])) continue;
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns how many elements are currently stored in the ArraySet.
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Iterator.
     */

    protected class ArraySetIterator implements Iterator<E> {

        int cursor = 0;

        /**
         * Return whether the iterator has more indexes to go through.
         */
        @Override
        public boolean hasNext() {
            return (cursor != size);
        }

        /**
         * Return the index of the last accessed data array entry.
         * Will return -1 if none have been accessed yet.
         */
        public int lastIndex() {
            return cursor - 1;
        }

        /**
         * Attempt to access the data object stored at the current index.
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            cursor++;
            return (E) data[cursor - 1];
        }

        /**
         * Return the index of the next data array entry that will be accessed by the next() function.
         */
        public int nextIndex() {
            return cursor;
        }

        @Override
        public void remove() {
            cursor--;
            ArraySet.this.remove(cursor);
        }
    }

    /*
     * Static methods.
     */

    /**
     * Return an ArraySet composed of the given varargs.
     */
    @SafeVarargs
    public static <T> ArraySet<T> asArraySet(T... array) {
        return new ArraySet<>(array);
    }
}