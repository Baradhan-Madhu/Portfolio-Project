/**
 * JUnit test fixture for the secondary methods of {@code ArtificialNeuron}.
 * Tests are written against the {@code ArtificialNeuron1L} implementation, but
 * rely only on the public {@code ArtificialNeuron} interface so that the same
 * tests would apply to any future implementation.
 */
public class ArtificialNeuronTest {

    /**
     * Tolerance for floating-point comparisons.
     */
    private static final double DELTA = 0.0001;

    /**
     * Helper that builds a neuron containing the given weights.
     *
     * @param ws
     *            the weights, in order
     * @return a new neuron with those weights
     */
    private static ArtificialNeuron neuronOf(double... ws) {
        ArtificialNeuron n = new ArtificialNeuron1L();
        for (double w : ws) {
            n.addWeight(w);
        }
        return n;
    }

    /*
     * scaleWeights tests -----------------------------------------------------
     */

    @Test
    public void testScaleWeightsEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.scaleWeights(2.0);
        assertEquals(0, n.weightCount());
    }

    @Test
    public void testScaleWeightsByTwo() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron expected = neuronOf(1.0, -2.0, 5.0);
        n.scaleWeights(2.0);
        assertEquals(expected, n);
    }

    @Test
    public void testScaleWeightsByZero() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron expected = neuronOf(0.0, 0.0, 0.0);
        n.scaleWeights(0.0);
        assertEquals(expected, n);
    }

    @Test
    public void testScaleWeightsByOneIsIdentity() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron expected = neuronOf(0.5, -1.0, 2.5);
        n.scaleWeights(1.0);
        assertEquals(expected, n);
    }

    @Test
    public void testScaleWeightsByNegative() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron expected = neuronOf(-0.5, 1.0, -2.5);
        n.scaleWeights(-1.0);
        assertEquals(expected, n);
    }

    /*
     * sumWeights tests -------------------------------------------------------
     */

    @Test
    public void testSumWeightsEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        assertEquals(0.0, n.sumWeights(), DELTA);
    }

    @Test
    public void testSumWeightsSingle() {
        ArtificialNeuron n = neuronOf(3.5);
        assertEquals(3.5, n.sumWeights(), DELTA);
    }

    @Test
    public void testSumWeightsMixedSigns() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        assertEquals(2.0, n.sumWeights(), DELTA);
    }

    @Test
    public void testSumWeightsAllNegative() {
        ArtificialNeuron n = neuronOf(-1.0, -2.0, -3.0);
        assertEquals(-6.0, n.sumWeights(), DELTA);
    }

    @Test
    public void testSumWeightsDoesNotModify() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron expected = neuronOf(0.5, -1.0, 2.5);
        n.sumWeights();
        assertEquals(expected, n);
    }

    /*
     * dotProduct tests -------------------------------------------------------
     */

    @Test
    public void testDotProductSinglePair() {
        ArtificialNeuron n = neuronOf(2.0);
        double result = n.dotProduct(new double[] { 3.0 });
        assertEquals(6.0, result, DELTA);
    }

    @Test
    public void testDotProductMultiple() {
        ArtificialNeuron n = neuronOf(1.0, 2.0, 3.0);
        // 1*4 + 2*5 + 3*6 = 32
        double result = n.dotProduct(new double[] { 4.0, 5.0, 6.0 });
        assertEquals(32.0, result, DELTA);
    }

    @Test
    public void testDotProductWithZeroInputs() {
        ArtificialNeuron n = neuronOf(1.0, 2.0, 3.0);
        double result = n.dotProduct(new double[] { 0.0, 0.0, 0.0 });
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testDotProductWithNegatives() {
        ArtificialNeuron n = neuronOf(1.0, -2.0, 3.0);
        // 1*2 + (-2)*1 + 3*(-1) = 2 - 2 - 3 = -3
        double result = n.dotProduct(new double[] { 2.0, 1.0, -1.0 });
        assertEquals(-3.0, result, DELTA);
    }

    @Test
    public void testDotProductDoesNotModify() {
        ArtificialNeuron n = neuronOf(1.0, 2.0, 3.0);
        ArtificialNeuron expected = neuronOf(1.0, 2.0, 3.0);
        n.dotProduct(new double[] { 4.0, 5.0, 6.0 });
        assertEquals(expected, n);
    }

    /*
     * toString tests ---------------------------------------------------------
     */

    @Test
    public void testToStringEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        assertEquals("<>", n.toString());
    }

    @Test
    public void testToStringSingle() {
        ArtificialNeuron n = neuronOf(0.5);
        assertEquals("<0.5>", n.toString());
    }

    @Test
    public void testToStringMultiple() {
        ArtificialNeuron n = neuronOf(0.5, -1.0, 2.5);
        assertEquals("<0.5, -1.0, 2.5>", n.toString());
    }

    /*
     * equals tests -----------------------------------------------------------
     */

    @Test
    public void testEqualsSelf() {
        ArtificialNeuron n = neuronOf(0.5, -1.0);
        assertTrue(n.equals(n));
    }

    @Test
    public void testEqualsBothEmpty() {
        ArtificialNeuron n1 = new ArtificialNeuron1L();
        ArtificialNeuron n2 = new ArtificialNeuron1L();
        assertTrue(n1.equals(n2));
    }

    @Test
    public void testEqualsSameWeights() {
        ArtificialNeuron n1 = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron n2 = neuronOf(0.5, -1.0, 2.5);
        assertTrue(n1.equals(n2));
    }

    @Test
    public void testEqualsDifferentSize() {
        ArtificialNeuron n1 = neuronOf(0.5, -1.0);
        ArtificialNeuron n2 = neuronOf(0.5, -1.0, 2.5);
        assertFalse(n1.equals(n2));
    }

    @Test
    public void testEqualsDifferentWeights() {
        ArtificialNeuron n1 = neuronOf(0.5, -1.0);
        ArtificialNeuron n2 = neuronOf(0.5, 1.0);
        assertFalse(n1.equals(n2));
    }

    @Test
    public void testEqualsAgainstNonNeuron() {
        ArtificialNeuron n = neuronOf(0.5);
        assertFalse(n.equals("0.5"));
    }

    @Test
    public void testEqualsAgainstNull() {
        ArtificialNeuron n = neuronOf(0.5);
        assertFalse(n.equals(null));
    }

    /*
     * hashCode tests ---------------------------------------------------------
     */

    @Test
    public void testHashCodeConsistentWithEquals() {
        ArtificialNeuron n1 = neuronOf(0.5, -1.0, 2.5);
        ArtificialNeuron n2 = neuronOf(0.5, -1.0, 2.5);
        assertEquals(n1.hashCode(), n2.hashCode());
    }

    @Test
    public void testHashCodeEmptyConsistent() {
        ArtificialNeuron n1 = new ArtificialNeuron1L();
        ArtificialNeuron n2 = new ArtificialNeuron1L();
        assertEquals(n1.hashCode(), n2.hashCode());
    }

}