/**
 * {@code ArtificialNeuronKernel} enhanced with secondary methods. Adds common
 * convenience operations for working with the weight vector of an artificial
 * neuron, such as uniform scaling, summation, and identifying the largest
 * weight. All secondary methods are implementable using only the kernel methods
 * of {@code ArtificialNeuronKernel} and the methods inherited from
 * {@code Standard}.
 *
 * @author Baradhan Madhavan
 */
public interface ArtificialNeuron extends ArtificialNeuronKernel {

    /**
     * Multiplies every weight in {@code this} by the given {@code factor}.
     *
     * @param factor
     *            the scalar by which every weight will be multiplied
     * @updates this
     * @ensures <pre>
     * |this| = |#this|  and
     * for all k: integer
     *     where (0 <= k and k < |this|)
     *   (entry(this, k) = entry(#this, k) * factor)
     * </pre>
     */
    void scaleWeights(double factor);

    /**
     * Reports the sum of all weights currently stored in {@code this}.
     *
     * @return the sum of all weights in {@code this}
     * @ensures sumWeights = [the sum of all entries in this, or 0.0 if |this| =
     *          0]
     */
    double sumWeights();

    /**
     * Reports the largest weight currently stored in {@code this}.
     *
     * @return the maximum weight value in {@code this}
     * @requires |this| > 0
     * @ensures <pre>
     * maxWeight is an entry of this  and
     * for all k: integer
     *     where (0 <= k and k < |this|)
     *   (entry(this, k) <= maxWeight)
     * </pre>
     */
    double maxWeight();

}