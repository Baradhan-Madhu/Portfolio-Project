/**
 * Layered implementations of secondary methods and common {@code Object}
 * methods for {@code ArtificialNeuron}.
 */
public abstract class ArtificialNeuronSecondary implements ArtificialNeuron {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        for (int i = 0; i < this.weightCount(); i++) {
            result.append(this.getWeight(i));
            if (i < this.weightCount() - 1) {
                result.append(", ");
            }
        }
        result.append(">");
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArtificialNeuron)) {
            return false;
        }
        ArtificialNeuron other = (ArtificialNeuron) obj;
        if (this.weightCount() != other.weightCount()) {
            return false;
        }
        for (int i = 0; i < this.weightCount(); i++) {
            if (Double.compare(this.getWeight(i), other.getWeight(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < this.weightCount(); i++) {
            result = 31 * result + Double.hashCode(this.getWeight(i));
        }
        return result;
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    @Override
    public void scaleWeights(double factor) {
        for (int i = 0; i < this.weightCount(); i++) {
            this.setWeight(i, this.getWeight(i) * factor);
        }
    }

    @Override
    public double sumWeights() {
        double sum = 0.0;
        for (int i = 0; i < this.weightCount(); i++) {
            sum += this.getWeight(i);
        }
        return sum;
    }

    @Override
    public double dotProduct(double[] inputs) {
        assert inputs != null : "Violation of: inputs is not null";
        assert inputs.length == this
                .weightCount() : "Violation of: inputs.length = |this|";
        double sum = 0.0;
        for (int i = 0; i < this.weightCount(); i++) {
            sum += this.getWeight(i) * inputs[i];
        }
        return sum;
    }

}