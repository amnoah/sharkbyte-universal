package sharkbyte.universal.data;

/**
 * A basic pair implementation, holding 2 objects inside the same class.
 *
 * @Authors: am noah
 * @Since: 1.0.0
 * @Updated: 1.0.0
 */
public class Pair<A, B> {

    private A first;
    private B second;

    /*
     * Initialize the object.
     */

    /**
     * Initialize the Pair object with no inputs.
     */
    public Pair() {
        first = null;
        second = null;
    }

    /**
     * Initialize the Pair object with both the A object input and B object input.
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /*
     * Getters.
     */

    /**
     * Return the stored A object.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Return the stored B object.
     */
    public B getSecond() {
        return second;
    }

    /*
     * Setters.
     */

    /**
     * Store the inputted A object.
     */
    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * Store the inputted B object.
     */
    public void setSecond(B second) {
        this.second = second;
    }
}