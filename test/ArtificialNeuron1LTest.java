/**
 * JUnit test fixture for {@code ArtificialNeuron1L}. Verifies the kernel
 * methods ({@code addWeight}, {@code setWeight}, {@code getWeight},
 * {@code weightCount}) and the {@code Standard} methods ({@code clear},
 * {@code newInstance}, {@code transferFrom}).
 */
public class ArtificialNeuron1LTest {

    /**
     * Tolerance for floating-point comparisons.
     */
    private static final double DELTA = 0.0001;

    /*
     * Constructor tests ------------------------------------------------------
     */

    @Test
    public void testConstructorEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        assertEquals(0, n.weightCount());
    }

    /*
     * addWeight tests --------------------------------------------------------
     */

    @Test
    public void testAddWeightToEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        assertEquals(1, n.weightCount());
        assertEquals(0.5, n.getWeight(0), DELTA);
    }

    @Test
    public void testAddWeightMultiple() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        n.addWeight(2.5);
        assertEquals(3, n.weightCount());
        assertEquals(0.5, n.getWeight(0), DELTA);
        assertEquals(-1.0, n.getWeight(1), DELTA);
        assertEquals(2.5, n.getWeight(2), DELTA);
    }

    @Test
    public void testAddWeightMaintainsOrder() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        for (int i = 0; i < 10; i++) {
            n.addWeight(i * 0.1);
        }
        assertEquals(10, n.weightCount());
        for (int i = 0; i < 10; i++) {
            assertEquals(i * 0.1, n.getWeight(i), DELTA);
        }
    }

    /*
     * setWeight tests --------------------------------------------------------
     */

    @Test
    public void testSetWeightSingle() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.setWeight(0, 1.5);
        assertEquals(1, n.weightCount());
        assertEquals(1.5, n.getWeight(0), DELTA);
    }

    @Test
    public void testSetWeightFirst() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        n.addWeight(2.5);
        n.setWeight(0, 99.0);
        assertEquals(99.0, n.getWeight(0), DELTA);
        assertEquals(-1.0, n.getWeight(1), DELTA);
        assertEquals(2.5, n.getWeight(2), DELTA);
    }

    @Test
    public void testSetWeightMiddle() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        n.addWeight(2.5);
        n.setWeight(1, 99.0);
        assertEquals(0.5, n.getWeight(0), DELTA);
        assertEquals(99.0, n.getWeight(1), DELTA);
        assertEquals(2.5, n.getWeight(2), DELTA);
    }

    @Test
    public void testSetWeightLast() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        n.addWeight(2.5);
        n.setWeight(2, 99.0);
        assertEquals(0.5, n.getWeight(0), DELTA);
        assertEquals(-1.0, n.getWeight(1), DELTA);
        assertEquals(99.0, n.getWeight(2), DELTA);
    }

    /*
     * getWeight tests --------------------------------------------------------
     */

    @Test
    public void testGetWeightDoesNotModify() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        double first = n.getWeight(0);
        double firstAgain = n.getWeight(0);
        assertEquals(first, firstAgain, DELTA);
        assertEquals(2, n.weightCount());
    }

    /*
     * weightCount tests ------------------------------------------------------
     */

    @Test
    public void testWeightCountEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        assertEquals(0, n.weightCount());
    }

    @Test
    public void testWeightCountAfterMany() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        for (int i = 0; i < 100; i++) {
            n.addWeight(i);
        }
        assertEquals(100, n.weightCount());
    }

    /*
     * clear tests ------------------------------------------------------------
     */

    @Test
    public void testClearOnEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.clear();
        assertEquals(0, n.weightCount());
    }

    @Test
    public void testClearOnNonEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        n.addWeight(2.5);
        n.clear();
        assertEquals(0, n.weightCount());
    }

    /*
     * newInstance tests ------------------------------------------------------
     */

    @Test
    public void testNewInstanceFromEmpty() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        ArtificialNeuron fresh = n.newInstance();
        assertEquals(0, fresh.weightCount());
        assertNotSame(n, fresh);
    }

    @Test
    public void testNewInstanceFromNonEmptyDoesNotAffectOriginal() {
        ArtificialNeuron n = new ArtificialNeuron1L();
        n.addWeight(0.5);
        n.addWeight(-1.0);
        ArtificialNeuron fresh = n.newInstance();
        assertEquals(0, fresh.weightCount());
        assertEquals(2, n.weightCount());
        assertEquals(0.5, n.getWeight(0), DELTA);
        assertEquals(-1.0, n.getWeight(1), DELTA);
    }

    /*
     * transferFrom tests -----------------------------------------------------
     */

    @Test
    public void testTransferFromEmptyToEmpty() {
        ArtificialNeuron source = new ArtificialNeuron1L();
        ArtificialNeuron target = new ArtificialNeuron1L();
        target.transferFrom(source);
        assertEquals(0, target.weightCount());
        assertEquals(0, source.weightCount());
    }

    @Test
    public void testTransferFromNonEmpty() {
        ArtificialNeuron source = new ArtificialNeuron1L();
        source.addWeight(0.5);
        source.addWeight(-1.0);
        source.addWeight(2.5);
        ArtificialNeuron target = new ArtificialNeuron1L();
        target.transferFrom(source);
        assertEquals(3, target.weightCount());
        assertEquals(0.5, target.getWeight(0), DELTA);
        assertEquals(-1.0, target.getWeight(1), DELTA);
        assertEquals(2.5, target.getWeight(2), DELTA);
        assertEquals(0, source.weightCount());
    }

    @Test
    public void testTransferFromOverwritesTarget() {
        ArtificialNeuron source = new ArtificialNeuron1L();
        source.addWeight(99.0);
        ArtificialNeuron target = new ArtificialNeuron1L();
        target.addWeight(1.0);
        target.addWeight(2.0);
        target.transferFrom(source);
        assertEquals(1, target.weightCount());
        assertEquals(99.0, target.getWeight(0), DELTA);
        assertEquals(0, source.weightCount());
    }

}