import java.util.ArrayList;

/**
 * {@code ArtificialNeuron} represented as an {@code ArrayList} of
 * {@code Double} values.
 *
 * @convention $this.weights is not null
 * @correspondence this = entries($this.weights)
 */
public class ArtificialNeuron1L extends ArtificialNeuronSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private ArrayList<Double> weights;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.weights = new ArrayList<Double>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public ArtificialNeuron1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final ArtificialNeuron newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type: " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(ArtificialNeuron source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof ArtificialNeuron1L : ""
                + "Violation of: source is of dynamic type "
                + "ArtificialNeuron1L";
        ArtificialNeuron1L localSource = (ArtificialNeuron1L) source;
        this.weights = localSource.weights;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void addWeight(double w) {
        this.weights.add(w);
    }

    @Override
    public final void setWeight(int index, double w) {
        assert 0 <= index : "Violation of: 0 <= index";
        assert index < this.weights.size() : "Violation of: index < |this|";
        this.weights.set(index, w);
    }

    @Override
    public final double getWeight(int index) {
        assert 0 <= index : "Violation of: 0 <= index";
        assert index < this.weights.size() : "Violation of: index < |this|";
        return this.weights.get(index);
    }

    @Override
    public final int weightCount() {
        return this.weights.size();
    }

}