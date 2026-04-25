/**
 * {@code ArtificialNeuron} kernel component with primary methods. Models a
 * single artificial neuron as a mutable sequence of real-valued weights, one
 * per input connection. The kernel provides the minimal functionality required
 * to build, inspect, and update the weight vector.
 *
 * @mathmodel type ArtificialNeuronKernel is modeled by string of real
 * @initially <pre>
 * ():
 *  ensures
 *   this = <>
 * </pre>
 *
 * @author Baradhan Madhavan
 */
public interface ArtificialNeuronKernel extends Standard<ArtificialNeuron> {

    /**
     * Appends the given weight {@code w} to the end of {@code this}.
     *
     * @param w
     *            the weight value to append
     * @updates this
     * @ensures this = #this * <w>
     */
    void addWeight(double w);

    /**
     * Reports the weight at position {@code index} of {@code this}.
     *
     * @param index
     *            the position of the weight to report
     * @return the weight at position {@code index}
     * @requires 0 <= index and index < |this|
     * @ensures getWeight = entry(this, index)
     */
    double getWeight(int index);

    /**
     * Replaces the weight at position {@code index} of {@code this} with the
     * given weight {@code w}.
     *
     * @param index
     *            the position of the weight to replace
     * @param w
     *            the new weight value
     * @updates this
     * @requires 0 <= index and index < |this|
     * @ensures <pre>
     * |this| = |#this|  and
     * entry(this, index) = w  and
     * for all k: integer
     *     where (0 <= k and k < |this| and k /= index)
     *   (entry(this, k) = entry(#this, k))
     * </pre>
     */
    void setWeight(int index, double w);

    /**
     * Reports the number of weights currently stored in {@code this}.
     *
     * @return the number of weights in {@code this}
     * @ensures weightCount = |this|
     */
    int weightCount();

}